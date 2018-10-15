package com.ys.sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author HD
 * @date 2018/10/15 21:18
 */
public class ProductInfoDto {

    @JsonProperty("主键")
    private String productId;

    @JsonProperty("商品名称")
    private String productName;

    @JsonProperty("单价")
    private BigDecimal productPrice;

    @JsonProperty("描述")
    private String productDescription;

    @JsonProperty("小图片")
    private String productIcon;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }
}
