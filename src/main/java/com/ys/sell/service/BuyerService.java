package com.ys.sell.service;

import com.ys.sell.dto.OrderDto;

/**
 * @author HD
 * @date 2018/10/18 22:43
 */
public interface BuyerService {

    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto cancelOrder(String openid, String orderId);
}
