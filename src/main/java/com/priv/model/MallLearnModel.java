package com.priv.model;

import com.priv.model.vo.ChannelFlowVo;

import java.util.List;

/**
 * @author Json
 * @date 2021/6/15 14:05
 */
public class MallLearnModel {

    private String total;

    private List<ChannelFlowVo> channelFlowVos;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ChannelFlowVo> getChannelFlowVos() {
        return channelFlowVos;
    }

    public void setChannelFlowVos(List<ChannelFlowVo> channelFlowVos) {
        this.channelFlowVos = channelFlowVos;
    }
}
