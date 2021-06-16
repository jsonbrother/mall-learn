package com.priv.model.vo;

/**
 * 渠道流水视图
 *
 * @author Json
 * @date 2021/6/15 11:22
 */
public class ChannelFlowVo {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 流水编号
     */
    private String flowCode;

    /**
     * 交易类型
     */
    private String txnType;

    /**
     * 交易时间
     */
    private String txnTime;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 支付金额
     */
    private String payAmount;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
