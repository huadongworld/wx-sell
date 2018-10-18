package com.ys.sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author HD
 * @date 2018/10/15 21:17
 */
public class ProductDto {

    @JsonProperty("类目名称")
    private String categoryName;

    @JsonProperty("类目类型")
    private Integer categoryType;

    @JsonProperty("商品")
    private List<ProductInfoDto> productInfoDtoList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoDto> getProductInfoDtoList() {
        return productInfoDtoList;
    }

    public void setProductInfoDtoList(List<ProductInfoDto> productInfoDtoList) {
        this.productInfoDtoList = productInfoDtoList;
    }
}
