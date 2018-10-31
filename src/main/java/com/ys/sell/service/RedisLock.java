package com.ys.sell.service;

import org.simpleframework.xml.core.Commit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author HD
 * @date 2018/10/29 21:51
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {

        //setIfAbsent对应redis的SETNX命令（set if not exist)
        //详见：http://www.redis.cn/commands/setnx.html
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //锁住
            return true;
        }

        //获取当前锁的时间
        String currentValue = redisTemplate.opsForValue().get(key);

        //如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {

            //获取上一个锁的时间
            //getAndSet对应redis的GETSET命令（先get再set)
            //详见：http://www.redis.cn/commands/getset.html
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);

            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {

        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常：{}", e);
        }
    }
}
