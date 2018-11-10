package com.ys.sell.exception;

/**
 * @author HD
 * @date 2018/11/6 9:28
 */
public class BaseException extends RuntimeException{

    Object resultObject;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }
}
