package com.boot.payment.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * 微信响应实体
 *
 * @author lizhifu
 * @date 2021/1/20
 */
@Slf4j
@Data
public class WxResponseEntity<T> {
    /**
     * httpStatus
     */
    private int httpStatus;
    /**
     * body
     */
    private T body;

    /**
     * 响应转换
     * @param responseEntity
     */
    public void convert(ResponseEntity<T> responseEntity) {
        log.info("wechat response {}", responseEntity);
        if (Objects.nonNull(responseEntity)) {
            this.httpStatus = responseEntity.getStatusCodeValue();
            this.body = responseEntity.getBody();
        } else {
            this.httpStatus = HttpStatus.REQUEST_TIMEOUT.value();
            this.body = null;
        }
    }
}
