package com.ys.sell.common;

import java.io.Serializable;

/**
 * @author HD
 * @date 2018/10/15 21:15
 */
public class ResultObject<T> implements Serializable {

    private static final long serialVersionUID = -4982498107374918692L;
    
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
