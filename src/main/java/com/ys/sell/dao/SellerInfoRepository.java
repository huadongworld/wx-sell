package com.ys.sell.dao;

import com.ys.sell.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author HD
 * @date 2018/10/21 9:25
 */
@Component
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String abc);
}
