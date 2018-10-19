package com.ys.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ys.sell.command.OrderForm;
import com.ys.sell.dto.OrderDto;
import com.ys.sell.enums.ResultEnum;
import com.ys.sell.exception.SellException;
import com.ys.sell.model.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HD
 * @date 2018/10/18 22:37
 */
public class OrderForm2OrderDTOConverter {

    private static final Logger log = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);

    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDto orderDTO = new OrderDto();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {}
                    .getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
