package com.priv.service.order.impl;

import com.priv.service.order.PlaceOrderStrategy;
import com.priv.service.order.StrategyFactory;

/**
 * @author Json
 * @date 2021/4/20 16:57
 */
public class PlaceOrderStrategyFactory extends StrategyFactory {

    @Override
    public PlaceOrderStrategy createStrategy(Class c) {
        PlaceOrderStrategy strategy = null;
        try {
            strategy = (PlaceOrderStrategy) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strategy;
    }
}
