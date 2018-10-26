package com.ys.sell.service.impl;

import com.ys.sell.dto.OrderDto;
import com.ys.sell.service.OrderService;
import com.ys.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author HD
 * @date 2018/10/26 15:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDto orderDTO = orderService.findOne("1499097366838352541");
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDto orderDTO = orderService.findOne("1499592887470659070");
        payService.refund(orderDTO);
    }

}