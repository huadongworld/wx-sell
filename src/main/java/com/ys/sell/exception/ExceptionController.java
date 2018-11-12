package com.ys.sell.exception;

import com.ys.sell.json.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HD
 * @date 2018/11/12 20:56
 */
@ResponseBody
@ControllerAdvice
public class ExceptionController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @Autowired(required = false)
    private ExceptionReporter exceptionReporter;

    @Autowired
    private MessageSource webCommonMessageSource;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public JsonResponse error(
            Throwable throwable,
            HttpServletRequest httpServletRequest
    )
    {
        String errorMessage = "服务器内部错误";

        if (exceptionReporter != null) {
            errorMessage = exceptionReporter.buildErrorMessage(httpServletRequest);
        }

        LOGGER.error(
                errorMessage,
                throwable
        );

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setIntCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        jsonResponse.setMsg(webCommonMessageSource.getMessage(
                "500",
                null,
                LocaleContextHolder.getLocale()
        ));
        return jsonResponse;
    }

    @ExceptionHandler({
                              HttpRequestMethodNotSupportedException.class,
                              HttpMediaTypeNotSupportedException.class,
                              HttpMediaTypeNotAcceptableException.class,
                              HttpMediaTypeException.class,
                      })
    public JsonResponse handleHttpRequestMethodNotSupportedException(Throwable throwable)
    {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setIntCode(HttpStatus.BAD_REQUEST.value());
        jsonResponse.setMsg(throwable.getMessage());
        return jsonResponse;
    }

    @ExceptionHandler(BusinessException.class)
    public JsonResponse handleBusinessException(BusinessException e)
    {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(e.getMsgCode());
        jsonResponse.setMsgArgs(e.getMsgArguments()
                                 .toArray());
        jsonResponse.setResultObject(e.getResultObject());
        return jsonResponse;
    }

}
