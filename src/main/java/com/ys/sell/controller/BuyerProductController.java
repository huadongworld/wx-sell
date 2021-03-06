package com.ys.sell.controller;

import com.ys.sell.common.ResultObject;
import com.ys.sell.dto.ProductDto;
import com.ys.sell.dto.ProductInfoDto;
import com.ys.sell.exception.BusinessException;
import com.ys.sell.exception.SellException;
import com.ys.sell.model.ProductCategory;
import com.ys.sell.model.ProductInfo;
import com.ys.sell.service.CategoryService;
import com.ys.sell.service.ProductService;
import com.ys.sell.utils.ResultObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//    @Cacheable(cacheNames = "product", key = "123")
    /**
     * //当sellerId的长度大于3时，缓存生效；unless：如果ResultObject的code为0，缓存生效（缓存正确数据）
     */
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")
    public ResultObject list(@PathVariable("sellerId") String sellerId) {

        //查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //查找所有上架商品中的类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //查询类目详情
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //类目菜单拼装
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductDto productDto = new ProductDto();
            productDto.setCategoryType(productCategory.getCategoryType());
            productDto.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoDto> productInfoDtoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    BeanUtils.copyProperties(productInfo, productInfoDto);
                    productInfoDtoList.add(productInfoDto);
                }
            }
            productDto.setProductInfoDtoList(productInfoDtoList);
            productDtoList.add(productDto);
        }

        return ResultObjectUtils.success(productDtoList);
    }

    @GetMapping("/userI18n")
    public ResultObject userI18n() {
        throw new BusinessException("ESM00001");
    }

    @GetMapping("/i18n")
    public ResultObject i18n() {
        throw new SellException(100, "啦啦啦");
    }
}
