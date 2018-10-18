package com.ys.sell.service;

import com.ys.sell.dto.CartDto;
import com.ys.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author HD
 * @date 2018/10/15 20:50
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDto> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDto> cartDTOList);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}
