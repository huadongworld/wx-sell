package com.ys.sell.controller;

import com.ys.sell.common.ResultObject;
import com.ys.sell.dto.ProductDto;
import com.ys.sell.dto.ProductInfoDto;
import com.ys.sell.model.ProductCategory;
import com.ys.sell.model.ProductInfo;
import com.ys.sell.service.CategoryService;
import com.ys.sell.service.ProductService;
import com.ys.sell.utils.ResultObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HD
 * @date 2018/10/15 21:12
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultObject list() {

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductDto> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductDto productVO = new ProductDto();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoDto> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoDto productInfoVO = new ProductInfoDto();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoDtos(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultObjectUtils.success(productVOList);
    }
}
