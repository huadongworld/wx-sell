package com.ys.sell.service;

import com.ys.sell.dto.OrderDto;

/**
 * @author HD
 * @date 2018/10/21 17:25
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDto
     */
    void orderStatus(OrderDto orderDto);
}
