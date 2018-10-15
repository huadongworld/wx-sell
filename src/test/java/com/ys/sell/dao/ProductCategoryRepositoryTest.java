package com.ys.sell.dao;

import com.ys.sell.model.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("热销商品", 2);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
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
    public void findByCategoryTypeInTest() {
        List<ProductCategory> productCategories = repository.findByCategoryTypeIn(Lists.newArrayList(1, 2));
        Assert.assertNotEquals(0, productCategories.size());
        Assert.assertNotEquals(2, productCategories.size());
    }
}