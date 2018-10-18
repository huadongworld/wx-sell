package com.ys.sell.utils;

import com.ys.sell.common.ResultObject;

/**
 * @author HD
 * @date 2018/10/15 21:21
 */
public class ResultObjectUtils {

    public static ResultObject success(Object object) {
        ResultObject resultObject = new ResultObject();
        resultObject.setData(object);
        resultObject.setCode(0);
        resultObject.setMsg("成功");
        return resultObject;
    }

    public static ResultObject success() {
        return success(null);
    }

    public static ResultObject error(Integer code, String msg) {
        ResultObject resultVO = new ResultObject();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
