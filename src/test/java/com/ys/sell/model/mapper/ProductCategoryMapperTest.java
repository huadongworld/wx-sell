package com.ys.sell.model.mapper;

import com.ys.sell.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author HD
 * @date 2018/10/28 20:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "老人最爱");
        map.put("category_type", "101");
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("饮料小吃");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {

        ProductCategory result = productCategoryMapper.findByCategoryType(102);
        Assert.assertEquals("饮料小吃", result.getCategoryName());
    }

    @Test
    public void findByCategoryName() {

        List<ProductCategory> result = productCategoryMapper.findByCategoryName("饮料小吃");
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void updateByCategoryType() {

        int result = productCategoryMapper.updateByCategoryType("饮料小吃", 102);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("饮料小吃");
        productCategory.setCategoryType(102);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {

        int result = productCategoryMapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }
}
