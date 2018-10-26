package com.ys.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.ys.sell.dto.OrderDto;

/**
 * @author HD
 * @date 2018/10/26 14:55
 */
public interface PayService {

    PayResponse create(OrderDto orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDTO);
}
