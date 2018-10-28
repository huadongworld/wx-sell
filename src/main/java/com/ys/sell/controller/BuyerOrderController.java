package com.ys.sell.controller;

import com.ys.sell.command.OrderForm;
import com.ys.sell.common.ResultObject;
import com.ys.sell.converter.OrderForm2OrderDTOConverter;
import com.ys.sell.dto.OrderDto;
import com.ys.sell.enums.ResultEnum;
import com.ys.sell.exception.SellException;
import com.ys.sell.service.BuyerService;
import com.ys.sell.service.OrderService;
import com.ys.sell.utils.ResultObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/18 22:34
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    private static final Logger log = LoggerFactory.getLogger(BuyerOrderController.class);

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultObject<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultObjectUtils.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultObject<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDto> orderDTOPage = orderService.findList(openid, request);

        return ResultObjectUtils.success(orderDTOPage.getContent());
    }


    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultObject<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDto orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultObjectUtils.success(orderDTO);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public ResultObject cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultObjectUtils.success();
    }
}
