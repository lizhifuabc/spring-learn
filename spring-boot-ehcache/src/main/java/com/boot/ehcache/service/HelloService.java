package com.boot.ehcache.service;

import com.boot.ehcache.domain.Hello;

/**
 * Hello
 *
 * @author lizhifu
 * @date 2020/12/9
 */
public interface HelloService {
    /**
     * 增/改
     *
     * @param hello 用户对象
     * @return Hello
     */
    Hello insert(Hello hello);

    /**
     * 查
     *
     * @param id key值
     * @return 返回结果
     */
    Hello select(Long id);

    /**
     * 删
     *
     * @param id key值
     */
    void delete(Long id);
}
