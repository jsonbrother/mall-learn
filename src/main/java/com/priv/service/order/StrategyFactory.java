package com.priv.service.order;

/**
 * 抽象工厂
 *
 * @author Json
 * @date 2021/4/20 16:58
 */
public abstract class StrategyFactory<T> {

    /**
     * 创建策略
     */
    public abstract PlaceOrderStrategy createStrategy(Class<T> c);
}
