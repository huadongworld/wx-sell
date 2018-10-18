package com.ys.sell.service.impl;

import com.ys.sell.dto.OrderDto;
import com.ys.sell.enums.OrderStatusEnum;
import com.ys.sell.enums.PayStatusEnum;
import com.ys.sell.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-11 19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1539872013046691384";

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Test
    public void create() throws Exception {

        OrderDto orderDTO = new OrderDto();
        orderDTO.setBuyerName("悟空");
        orderDTO.setBuyerAddress("花果山");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(10);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(10);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDto result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDto> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDTO = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDTO = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDTO = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDTOPage = orderService.findList(request);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }

}