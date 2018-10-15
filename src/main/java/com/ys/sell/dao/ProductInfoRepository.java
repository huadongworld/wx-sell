package com.ys.sell.dao;/**
 * @author HD
 * @date 2018/10/15 8:59
 */

import com.ys.sell.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author HD
 * @date 2018/10/15 8:59
 */
@Component
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
}
