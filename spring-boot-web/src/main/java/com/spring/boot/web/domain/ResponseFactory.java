package com.spring.boot.web.domain;

/**
 * 生成Response
 *
 * @author lizhifu
 * @since 2023/7/26
 */
public interface ResponseFactory {
    /**
     * 创建响应状态
     *
     * @return 响应
     */
    Response newEmptyInstance();

    /**
     * 创建响应状态
     *
     * @param responseStatus 响应行信息.
     * @return
     */
    Response newInstance(ResponseStatus responseStatus);

    /**
     * 创建新的响应.
     *
     * @return 响应
     */
    Response newSuccessInstance();

    /**
     * 从数据中创建成功响应.
     *
     * @param data 响应数据.
     * @return 响应
     */
    Response newSuccessInstance(Object data);

    /**
     * 创建新的失败响应.
     *
     * @return 响应
     */
    Response newFailInstance();
}
