package com.ys.sell.service.impl;

import com.ys.sell.dto.OrderDto;
import com.ys.sell.enums.ResultEnum;
import com.ys.sell.exception.SellException;
import com.ys.sell.service.BuyerService;
import com.ys.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HD
 * @date 2018/10/18 22:43
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(BuyerServiceImpl.class);

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {
            log.error("【取消订单】查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
