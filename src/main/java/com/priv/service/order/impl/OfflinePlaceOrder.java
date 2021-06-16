package com.priv.service.order.impl;

import com.priv.model.dto.PlaceOrderDTO;
import com.priv.service.order.PlaceOrderStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Json
 * @date 2021/4/20 17:07
 */
public class OfflinePlaceOrder extends PlaceOrderStrategy {

    private static Logger logger = LoggerFactory.getLogger(OfflinePlaceOrder.class);

    @Override
    public String placeOrder(PlaceOrderDTO placeOrderDTO) {
        logger.info("【线下订单】执行下单策略接口");
        logger.info("【线下订单】下单详细信息:{}", placeOrderDTO.toString());
        return "X20210425140700000001";
    }
}
