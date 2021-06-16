package com.priv.facade.order.impl;

import com.priv.facade.order.IOrderFacade;
import com.priv.model.dto.PlaceOrderDTO;
import com.priv.service.order.PlaceOrderStrategy;
import com.priv.service.order.common.PlaceOrderContext;
import com.priv.service.order.impl.OfflinePlaceOrder;
import com.priv.service.order.impl.OnlinePlaceOrder;
import com.priv.service.order.impl.PlaceOrderStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Json
 * @date 2021/4/20 16:51
 */
@Service
public class OrderFacadeImpl implements IOrderFacade {

    private static Logger logger = LoggerFactory.getLogger(OrderFacadeImpl.class);

    @Override
    public void createOrder(PlaceOrderDTO placeOrderDTO) {
        // 创建策略工厂
        PlaceOrderStrategyFactory strategyFactory = new PlaceOrderStrategyFactory();

        // 获得下单策略
        PlaceOrderStrategy rewardStrategy;
        if ("online".equals(placeOrderDTO.getSource())) {
            rewardStrategy = strategyFactory.createStrategy(OnlinePlaceOrder.class);
        } else if ("offline".equals(placeOrderDTO.getSource())) {
            rewardStrategy = strategyFactory.createStrategy(OfflinePlaceOrder.class);
        } else {
            logger.error("订单来源信息错误");
            throw new RuntimeException("订单来源信息错误");
        }

        // 获得具体的上下文
        PlaceOrderContext placeOrderContext = new PlaceOrderContext(rewardStrategy);

        // 执行下单策略
        placeOrderContext.doStrategy(placeOrderDTO);
    }
}
