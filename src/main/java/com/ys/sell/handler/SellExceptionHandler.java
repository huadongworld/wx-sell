package com.ys.sell.handler;

import com.ys.sell.common.ResultObject;
import com.ys.sell.config.ProjectUrlConfig;
import com.ys.sell.exception.SellException;
import com.ys.sell.exception.SellerAuthorizeException;
import com.ys.sell.utils.ResultObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author HD
 * @date 2018/10/21 17:14
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 拦截登录异常(SellerAuthorizeAspect中会抛出SellerAuthorizeException异常，抛出时使用这个modelAndView
     *
     * http://huadong.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://huadong.natapp1.cc/sell/seller/login
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN) //返回的调用状态
    public ResultObject handlerSellException(SellException e) {
        return ResultObjectUtils.error(e.getCode(), e.getMessage());
    }
}
