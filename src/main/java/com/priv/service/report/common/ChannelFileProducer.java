package com.priv.service.report.common;

import cn.hutool.core.text.StrSpliter;
import com.priv.model.vo.ChannelFileDetailVo;
import com.priv.service.report.impl.ChannelFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author Json
 * @date 2021/6/4 15:20
 */
public class ChannelFileProducer implements Callable<ChannelFileDetailVo> {

    private static Logger logger = LoggerFactory.getLogger(ChannelFileProducer.class);
    private CountDownLatch latch;
    private File file;
    private static final String SEPARATOR = "|";

    public ChannelFileProducer(CountDownLatch latch, File file) {
        this.latch = latch;
        this.file = file;
    }

    @Override
    public ChannelFileDetailVo call() throws Exception {
        ChannelFileDetailVo vo = new ChannelFileDetailVo();

        FileInputStream fis = new FileInputStream(file);
        List<List<String>> list = parsingInputStream(fis);

        vo.setFileName(file.getName());
        vo.setDetailList(list);

        logger.info("ChannelFileProducer ThreadName:{}", Thread.currentThread().getName());

        latch.countDown();
        return vo;
    }

    private List<List<String>> parsingInputStream(InputStream in) {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> data = StrSpliter.split(line, SEPARATOR, true, false);
                list.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
