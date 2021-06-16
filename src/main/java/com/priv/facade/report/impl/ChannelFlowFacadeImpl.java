package com.priv.facade.report.impl;

import com.priv.config.FileConfig;
import com.priv.facade.report.IChannelFlowFacade;
import com.priv.model.dto.ChannelFileAnalyseDTO;
import com.priv.model.dto.ChannelFileImportDTO;
import com.priv.service.report.IChannelFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Json
 * @date 2021/6/7 13:42
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelFlowFacadeImpl implements IChannelFlowFacade {

    private final IChannelFlowService channelFlowService;
    private final FileConfig fileConfig;

    @Override
    public void importChannelFile(ChannelFileImportDTO channelFileImportDTO) {
        ChannelFileAnalyseDTO channelFileAnalyseDTO = new ChannelFileAnalyseDTO();

        // 文件存放路径
        String departId = channelFileImportDTO.getDepartId();
        String workDate = channelFileImportDTO.getWorkDate();
        String filePath = fileConfig.getFilePath() + File.separator + departId + File.separator + workDate;
        channelFileAnalyseDTO.setFilePath(filePath);

        // 导入的文件名称
        Set<String> fileNames = new HashSet<>(channelFileImportDTO.getFileNames());
        channelFileAnalyseDTO.setFileNames(fileNames);

        channelFlowService.analyseChannelFile(channelFileAnalyseDTO);
    }
}
