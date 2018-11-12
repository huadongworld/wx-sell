package com.ys.sell.exception;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HD
 * @date 2018/11/12 20:56
 */
public interface ExceptionReporter
{
    String buildErrorMessage(HttpServletRequest httpServletRequest);
}
