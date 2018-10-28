package com.ys.sell.model.mapper;

import com.ys.sell.model.ProductCategory;
import com.ys.sell.model.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/28 20:24
 */
@Repository
public interface ProductCategoryMapper {

    /**
     * 新增（方式一：map)
     * @param map
     * @return
     */
    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    /**
     * 新增（方式二：对象)
     *
     * @param productCategory
     * @return
     */
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    /**
     * 查询单个
     *
     * @param categoryType
     * @return
     */
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_id", property = "categoryId")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 查询列表
     *
     * @param categoryName
     * @return
     */
    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_id", property = "categoryId")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    /**
     * 更新（根据字段）
     *
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

    /**
     * 更新（根据对象）
     *
     * @param productCategory
     * @return
     */
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    /**
     * 删除
     *
     * @param categoryType
     * @return
     */
    @Update("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

}
