package com.ys.sell.dao;/**
 * @author HD
 * @date 2018/10/15 8:59
 */

import com.ys.sell.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HD
 * @date 2018/10/15 8:59
 */
@Component
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询上架/下架的商品
     * @param code
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer code);
}
