package com.ys.sell.service.impl;

import com.ys.sell.dto.OrderDto;
import com.ys.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author HD
 * @date 2018/10/21 17:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() throws Exception {

        OrderDto orderDTO = orderService.findOne("1499097346204378750");
        pushMessageService.orderStatus(orderDTO);
    }

}