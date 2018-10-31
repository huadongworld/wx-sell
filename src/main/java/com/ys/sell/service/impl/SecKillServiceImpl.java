package com.ys.sell.service.impl;

import com.ys.sell.exception.SellException;
import com.ys.sell.service.RedisLock;
import com.ys.sell.service.SecKillService;
import com.ys.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/29 20:54
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    //商品
    static Map<String, Integer> products;
    //库存
    static Map<String, Integer> stock;

    static Map<String, String> orders;

    private static final int TIMEOUT = 10;

    @Autowired
    private RedisLock redisLock;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮皮虾特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + "份"
                + " 该商品成功下单用户数目："
                + orders.size() + "人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {

        //加锁
        Long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "人太多了，请稍后再试~~");
        }

        //1.查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            //2.下单（模拟不同用户，openid不同）
            orders.put(KeyUtil.genUniqueKey(), productId);
            //3.减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //解锁
        redisLock.unlock(productId, String.valueOf(time));
    }
}