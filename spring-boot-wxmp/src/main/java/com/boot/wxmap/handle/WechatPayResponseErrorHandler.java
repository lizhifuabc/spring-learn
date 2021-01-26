package com.boot.wxmap.handle;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * 自定义错误
 *
 * @author lizhifu
 * @date 2021/1/25
 */
public class WechatPayResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }
}
