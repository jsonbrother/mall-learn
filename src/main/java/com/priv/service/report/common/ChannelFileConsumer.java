package com.priv.service.report.common;

import cn.hutool.core.util.StrUtil;
import com.priv.model.vo.ChannelFileDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Json
 * @date 2021/6/8 13:15
 */
public class ChannelFileConsumer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ChannelFileConsumer.class);
    private BlockingQueue<Future<ChannelFileDetailVo>> queue;
    private List<String> columns;

    public ChannelFileConsumer(BlockingQueue<Future<ChannelFileDetailVo>> queue, List<String> columns) {
        this.queue = queue;
        this.columns = columns;
    }

    @Override
    public void run() {
        StringBuilder tailSb = new StringBuilder();
        try {
            Future<ChannelFileDetailVo> future = queue.take();
            ChannelFileDetailVo detailVo = future.get();

            String fileName = detailVo.getFileName();
            List<List<String>> detailList = detailVo.getDetailList();
            int size = detailList.size();

            for (int i = 0; i < size; i++) {
                tailSb.append(insertSqlTail(detailList.get(i)));
                if (i != size - 1) {
                    tailSb.append(",");
                }
            }

            String tableName = StrUtil.subBefore(fileName, "_", false);
            String insertSql = insertSqlHead(tableName).append(tailSb).toString();
            logger.info("ChannelFileConsumer ThreadName:{}", Thread.currentThread().getName());
            //logger.info("insert sql:{}", insertSql);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder insertSqlHead(String tableName) {
        StringBuilder headSb = new StringBuilder("insert into ");
        headSb.append(tableName);
        headSb.append("( ");

        int columnSize = columns.size();
        for (int i = 0; i < columnSize; i++) {
            headSb.append(columns.get(i));
            if (i != columnSize - 1) {
                headSb.append(", ");
            }
        }

        headSb.append(") values ");
        return headSb;
    }

    private StringBuilder insertSqlTail(List<String> detail) {
        StringBuilder tailSb = new StringBuilder("( ");

        int columnSize = columns.size();
        for (int i = 0; i < columnSize; i++) {
            tailSb.append(detail.get(i));
            if (i != columnSize - 1) {
                tailSb.append(", ");
            } else {
                tailSb.append("), ");
            }
        }

        return tailSb;
    }

}
