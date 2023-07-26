package com.spring.boot.web.domain;

/**
 * 提供的默认的ResponseBeanFactory实现
 *
 * @author lizhifu
 * @since 2023/7/26
 */
public class DefaultResponseFactory implements ResponseFactory{
    @Override
    public Response newEmptyInstance() {
        return null;
    }

    @Override
    public Response newInstance(ResponseStatus responseStatus) {
        return null;
    }

    @Override
    public Response newSuccessInstance() {
        return null;
    }

    @Override
    public Response newSuccessInstance(Object data) {
        return null;
    }

    @Override
    public Response newFailInstance() {
        return null;
    }
}
