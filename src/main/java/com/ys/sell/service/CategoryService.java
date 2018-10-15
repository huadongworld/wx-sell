package com.ys.sell.service;

import com.ys.sell.model.ProductCategory;

import java.util.List;

/**

/**
 * @author HD
 * @date 2018/10/15 20:27
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
