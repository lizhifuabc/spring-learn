package com.boot.ehcache.domain;

import lombok.Data;

/**
 * Hello
 *
 * @author lizhifu
 * @date 2020/12/9
 */
@Data
public class Hello {
    /**
     * id
     */
    private Long id;
    /**
     * 发出者
     */
    private String from;
    /**
     * 接受者
     */
    private String to;
}
