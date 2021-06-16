package com.priv.service.report;

import com.priv.model.dto.ChannelFileAnalyseDTO;

/**
 * 渠道流水业务层
 *
 * @author Json
 * @date 2021/6/4 15:28
 */
public interface IChannelFlowService {

    /**
     * 解析渠道流水文件
     *
     * @param channelFileAnalyseDTO 传输对象
     */
    public void analyseChannelFile(ChannelFileAnalyseDTO channelFileAnalyseDTO);

}
