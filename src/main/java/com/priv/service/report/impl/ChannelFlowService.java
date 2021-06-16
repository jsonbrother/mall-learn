package com.priv.service.report.impl;

import cn.hutool.core.util.StrUtil;
import com.priv.config.ThreadPoolConfig;
import com.priv.model.dto.ChannelFileAnalyseDTO;
import com.priv.model.vo.ChannelFileDetailVo;
import com.priv.service.report.IChannelFlowService;
import com.priv.service.report.common.ChannelFileConsumer;
import com.priv.service.report.common.ChannelFileProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author Json
 * @date 2021/6/4 15:29
 */
@Service
public class ChannelFlowService implements IChannelFlowService {

    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    private static Logger logger = LoggerFactory.getLogger(ChannelFlowService.class);
    private static final List<String> columns = new ArrayList<>();

    static {
        columns.add(0, "depart_id");
        columns.add(1, "work_date");
        columns.add(2, "pay_code");
        columns.add(3, "pay_amount");
        columns.add(4, "currency");
    }

    @Override
    public void analyseChannelFile(ChannelFileAnalyseDTO channelFileAnalyseDTO) {
        File file = new File(channelFileAnalyseDTO.getFilePath());

        File[] filePaths = file.listFiles();
        assert filePaths != null;
        Set<String> fileNames = channelFileAnalyseDTO.getFileNames();
        final List<File> filePathList = checkFilePathFormat(filePaths, fileNames);

        CountDownLatch latch = new CountDownLatch(filePathList.size());
        ThreadPoolExecutor executor = threadPoolConfig.customExecutor();

        long startTime = System.currentTimeMillis();
        BlockingQueue<Future<ChannelFileDetailVo>> queue = new ArrayBlockingQueue<>(100);
        for (File temp : filePathList) {
            // 提交任务到线程池
            Future<ChannelFileDetailVo> future = executor.submit(new ChannelFileProducer(latch, temp));
            // 将返回值future添加到queue
            queue.add(future);
            // 调用消费者线程
            executor.execute(new ChannelFileConsumer(queue, columns));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        logger.info("-------------当前执行耗时:{}", endTime - startTime);

        /*
        ThreadPoolExecutor组件化 不需要关闭
        // 关闭线程池
        executor.shutdownNow();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                logger.info("线程池没有关闭");
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        logger.info("线程池已经关闭");*/
    }

    private List<File> checkFilePathFormat(File[] filePaths, Set<String> fileNames) {
        List<File> filePathList = new ArrayList<>();

        for (File filePath : filePaths) {
            String fileName = StrUtil.subBefore(filePath.getName(), "-", false);
            if (fileNames.contains(fileName)) {
                filePathList.add(filePath);
            }
        }

        return filePathList;
    }
}
