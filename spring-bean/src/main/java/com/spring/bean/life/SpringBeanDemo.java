package com.spring.bean.life;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * spring Bean定义
 *
 * @author lizhifu
 * @since 2023/9/7
 */
@Service
@Slf4j
public class SpringBeanDemo implements InitializingBean, DisposableBean, BeanNameAware {
    /**
     * 构造函数,执行顺序 1
     */
    public SpringBeanDemo() {
        log.info("SpringBeanDemo 构造函数");
    }

    /**
     * BeanNameAware 接口回调，执行顺序 2
     * @param name beanName
     */
    @Override
    public void setBeanName(String name) {
        log.info("SpringBeanDemo setBeanName:{}",name);
    }
    /**
     * 初始化方法，执行顺序 3
     */
    @PostConstruct
    public void init(){
        log.info("SpringBeanDemo init");
    }
    /**
     * InitializingBean 接口回调，执行顺序 4
     * @throws Exception 异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("SpringBeanDemo afterPropertiesSet");
    }
    /**
     * 销毁方法，执行顺序 1
     */
    @PreDestroy
    public void destroyMethod(){
        log.info("SpringBeanDemo destroyMethod");
    }

    /**
     * DisposableBean 接口回调，执行顺序 2
     * @throws Exception 异常
     */
    @Override
    public void destroy() throws Exception {
        log.info("SpringBeanDemo destroy");
    }
}
