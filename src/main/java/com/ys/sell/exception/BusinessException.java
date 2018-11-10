package com.ys.sell.exception;


import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author HD
 * @date 2018/11/6 9:29
 */
public class BusinessException extends BaseException {

    protected String msgCode;

    /**
     * msgCode对应的消息内容需要替换的参数
     */
    protected List<Object> msgArguments;

    public BusinessException(String msgCode) {
        this(msgCode, Collections.EMPTY_LIST);
    }

    public BusinessException(String msgCode, @NotNull Object msgArgument) {
        this(msgCode, Arrays.asList(msgArgument));
    }

    public BusinessException(String msgCode, Map resultObject) {
        this(msgCode, Collections.EMPTY_LIST, resultObject);
    }

    public BusinessException(String msgCode, @NotNull Object msgArgument, Map resultObject) {
        this(msgCode, Arrays.asList(msgArgument), resultObject);
    }

    public BusinessException(String msgCode, List msgArguments) {
        super(msgCode + ",args:" + msgArguments);
        this.msgCode = msgCode;
        this.msgArguments = msgArguments;
    }

    public BusinessException(String msgCode, List msgArguments, Map resultObject) {
        super(msgCode + ",args:" + msgArguments);
        this.msgCode = msgCode;
        this.msgArguments = msgArguments;
        this.resultObject = resultObject;
    }

    public String getMsgCode() {
        return this.msgCode;
    }

    public List<Object> getMsgArguments() {
        return msgArguments;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "msgCode='" + msgCode + '\'' +
                ", msgArguments=" + msgArguments +
                '}';
    }
}
