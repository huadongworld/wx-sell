package com.ys.sell.enums;

/**
 * @author HD
 * @date 2018/10/14 20:23
 */
public enum OrderStatusEnum implements CodeEnum {

    /**
     * 订单状态
     */
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
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
