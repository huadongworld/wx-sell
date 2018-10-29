package com.ys.sell.controller;

import com.ys.sell.service.impl.SecKillServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HD
 * @date 2018/10/29 20:53
 */
@RestController
@RequestMapping("/skill")
public class SecKillController {

    @Autowired
    private SecKillServiceImpl secKillService;

    private static final Logger log = LoggerFactory.getLogger(SecKillController.class);

    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) {
        return secKillService.querySecKillProductInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) {

        log.info("@skill request, productId:" + productId);
        secKillService.orderProductMockDiffUser(productId);

        return secKillService.querySecKillProductInfo(productId);
    }
}
