package com.ichings.generator.main;

import com.ichings.generator.util.Check;
import com.ichings.generator.util.Result;
import com.ichings.generator.util.ResultUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回结果
 *
 * @author 宋欢
 */
@ControllerAdvice
public class WideResponseBody implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (Check.isNull(body)) {
            return null;
        }

        if (body instanceof Result) {
            Result<?> result = (Result<?>) body;
            return ResultUtils.dtoToVo(result);
        }

        return body;
    }

}
