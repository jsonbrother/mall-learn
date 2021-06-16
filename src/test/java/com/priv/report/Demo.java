package com.priv.report;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.priv.facade.report.IChannelFlowFacade;
import com.priv.model.dto.ChannelFileImportDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Json
 * @date 2021/6/4 16:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {

    @Autowired
    private IChannelFlowFacade channelFlowFacade;

    @Test
    public void test1() {
        ChannelFileImportDTO channelFileImportDTO = new ChannelFileImportDTO();
        channelFileImportDTO.setDepartId("10000192");
        channelFileImportDTO.setWorkDate("20210607");
        List<String> fileNames = new ArrayList<>();
        fileNames.add("transaction_20210607");
        fileNames.add("recharge_20210607");
        channelFileImportDTO.setFileNames(fileNames);
        channelFlowFacade.importChannelFile(channelFileImportDTO);
    }

    @Test
    public void test2() throws IOException {
        BufferedWriter out = null;
        int num = 1;
        try {
            for (int i = 0; i < 500000; i++) {
                if (i % 10000 == 0) {
                    String pathName = "E:\\tmp\\mall-file\\10000192\\transaction_20210607-" + num + ".txt";
                    File writeName = new File(pathName);
                    boolean newFile = writeName.createNewFile();
                    if (newFile) {
                        out = new BufferedWriter(new FileWriter(writeName));
                    }
                    num++;
                }
                String code = "T20210607" + StrUtil.padPre(i + 1 + "", 6, "0");
                assert out != null;
                out.write("10000192|20210607|" + code + "|10000|RMB\r\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
        }
    }

}
