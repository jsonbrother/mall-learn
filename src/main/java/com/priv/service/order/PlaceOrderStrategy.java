package com.priv.service.order;

import com.priv.model.dto.PlaceOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下单策略
 *
 * @author Json
 * @date 2021/4/20 16:59
 */
public abstract class PlaceOrderStrategy {

    private static Logger logger = LoggerFactory.getLogger(PlaceOrderStrategy.class);

    /***
     * 下单
     * @param placeOrderDTO 下单信息
     * @return 订单号
     */
    public abstract String placeOrder(PlaceOrderDTO placeOrderDTO);

    public void delayedNotice(String orderId) {
        logger.info("【订单通知】发送订单编号:{}的延迟通知;", orderId);

        // TODO 为保证通知百分百投送 可事先保存一条记录 后续轮询检查其状态
    }

}
