package com.ys.sell.service.impl;

import com.ys.sell.dao.SellerInfoRepository;
import com.ys.sell.model.SellerInfo;
import com.ys.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HD
 * @date 2018/10/21 9:28
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
