package com.ys.sell.converter;

import com.ys.sell.dto.OrderDto;
import com.ys.sell.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HD
 * @date 2018/10/18 21:10
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDto convert(OrderMaster orderMaster) {

        OrderDto orderDTO = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
