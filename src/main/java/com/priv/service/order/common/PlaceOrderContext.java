package com.priv.service.order.common;

import com.priv.model.dto.PlaceOrderDTO;
import com.priv.service.order.PlaceOrderStrategy;

/**
 * 订单下单上下文
 *
 * @author Json
 * @date 2021/4/20 17:20
 */
public class PlaceOrderContext {

    private PlaceOrderStrategy strategy;

    public PlaceOrderContext(PlaceOrderStrategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy(PlaceOrderDTO placeOrderDTO) {
        String orderId = strategy.placeOrder(placeOrderDTO);
        strategy.delayedNotice(orderId);
    }
}
