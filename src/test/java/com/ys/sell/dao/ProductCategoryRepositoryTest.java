package com.ys.sell.dao;

import com.ys.sell.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author HD
 * @date 2018/10/14 20:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> productCategoryOptional = repository.findById(1);
        if (productCategoryOptional.isPresent()) {
            System.out.println(productCategoryOptional.get());
        } else {
            System.out.println("不存在。。。");
        }
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void updateTest() {
        Optional<ProductCategory> productCategoryOptional = repository.findById(2);
        if (productCategoryOptional.isPresent()) {
            ProductCategory productCategory = productCategoryOptional.get();
            productCategory.setCategoryName("女生最爱");
            repository.save(productCategory);
        }
    }

    @Test
    public void deleteTest() {
        Optional<ProductCategory> productCategoryOptional = repository.findById(2);
        if (productCategoryOptional.isPresent()) {
            repository.delete(productCategoryOptional.get());
        }
    }
}