package com.ys.sell.exception;

import com.ys.sell.enums.ResultEnum;

/**
 * @author HD
 * @date 2018/10/18 21:00
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
