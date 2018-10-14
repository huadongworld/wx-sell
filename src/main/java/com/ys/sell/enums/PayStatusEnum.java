package com.ys.sell.enums;

/**
 * @author HD
 * @date 2018/10/14 20:35
 */
public enum PayStatusEnum implements CodeEnum {

    /**
     * 支付状态
     */
    WAIT(0, "等待支付"),

    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
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
