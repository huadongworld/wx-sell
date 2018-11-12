package com.ys.sell.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author HD
 * @date 2018/11/12 19:28
 */
@ControllerAdvice
public class JsonResponseAdvice implements ResponseBodyAdvice<JsonResponse>
{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSource webCommonMessageSource;

    @Override
    public JsonResponse beforeBodyWrite(
            JsonResponse jsonResponse,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    )
    {
        boolean emptyMsg = jsonResponse.getMsg() == null;
        if (emptyMsg)
        {
            boolean codeNotEmpty = !StringUtils.isEmpty(jsonResponse.getCode());
            if (codeNotEmpty)
            {
                resolveMsgCode(jsonResponse);
            }
        }
        return jsonResponse;
    }

    private void resolveMsgCode(JsonResponse jsonResponse)
    {
        try{
            resolveMsgCode(jsonResponse, messageSource);
        }catch(NoSuchMessageException e){
            resolveMsgCode(jsonResponse, webCommonMessageSource);
        }
    }

    private void resolveMsgCode(
            JsonResponse jsonResponse,
            MessageSource messageSource
    )
    {
        String msg = messageSource.getMessage(
                jsonResponse.getCode(),
                jsonResponse.getMsgArgs(),
                LocaleContextHolder.getLocale()
        );
        jsonResponse.setMsg(msg);
    }

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class converterType
    )
    {
        Class cls = returnType.getParameterType();
        return JsonResponse.class == cls;
    }
}
