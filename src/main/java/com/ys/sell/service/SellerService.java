package com.ys.sell.service;

import com.ys.sell.model.SellerInfo;

/**
 * @author HD
 * @date 2018/10/21 9:28
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
