package com.priv.order;

import com.priv.facade.order.IOrderFacade;
import com.priv.model.dto.PlaceOrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Json
 * @date 2021/4/24 18:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {

    @Autowired
    private IOrderFacade iOrderFacade;

    @Test
    public void create() {
        PlaceOrderDTO dto = new PlaceOrderDTO();
        dto.setData("购买iphone8");
        dto.setSource("online");
        iOrderFacade.createOrder(dto);
    }


}
