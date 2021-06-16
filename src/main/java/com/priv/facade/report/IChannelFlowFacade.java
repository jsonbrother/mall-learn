package com.priv.facade.report;

import com.priv.model.dto.ChannelFileImportDTO;

/**
 * @author Json
 * @date 2021/6/7 13:42
 */
public interface IChannelFlowFacade {

    /**
     * 导入渠道流水文件
     */
    public void importChannelFile(ChannelFileImportDTO channelFileImportDTO);
}
