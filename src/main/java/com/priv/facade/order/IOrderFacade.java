package com.priv.facade.order;

import com.priv.model.dto.PlaceOrderDTO;

/**
 * @author Json
 * @date 2021/4/20 16:51
 */
public interface IOrderFacade {

    void createOrder(PlaceOrderDTO placeOrderDTO);
}
