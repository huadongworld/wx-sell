package com.ys.sell.dao;

import com.ys.sell.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author HD
 * @date 2018/10/14 20:46
 */
@Component
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
