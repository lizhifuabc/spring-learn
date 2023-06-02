package com.learn.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * InitializingBean
 *
 * @author lizhifu
 * @date 2020/12/31
 */
@Component
@Slf4j
public class InitializingBeanDemo implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBeanDemo afterPropertiesSet()");
    }
}
