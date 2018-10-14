package com.ys.sell.enums;

/**
 * @author HD
 * @date 2018/10/14 20:37
 */
public enum ProductStatusEnum implements CodeEnum {

    /**
     * 上线状态
     */
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
