package com.spring.bean.life;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * InstantiationAwareBeanPostProcessor，该接口是BeanPostProcessor接口的子接口。
 * 它提供了在实例化bean之前和之后执行自定义逻辑的方法
 *
 * @author lizhifu
 * @since 2023/9/7
 */
@Component
@Slf4j
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    /**
     * 在实例化bean之前执行自定义逻辑
     * @param beanClass beanClass
     * @param beanName beanName
     * @return bean
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName){
        log.info("MyInstantiationAwareBeanPostProcessor 在实例化bean之前执行自定义逻辑：{}，{}",beanName,beanName);
        // 不需要对实例化的Bean进行任何修改或处理。通常情况下，如果没有需要对Bean进行特殊处理的需求，可以直接返回null。
        // 在实际应用中，根据具体的需求，也可以根据实际情况返回其他的值，例如返回一个已经实例化的对象，或者返回一个代理对象等。
        // 这取决于你想要在实例化Bean之前执行的自定义逻辑以及对Bean的处理需求。
        return null;
    }

    /**
     * 在实例化bean之后执行自定义逻辑
     * @param bean bean
     * @param beanName beanName
     * @return 是否继续执行后续的InstantiationAwareBeanPostProcessor
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName){
        log.info("MyInstantiationAwareBeanPostProcessor 在实例化bean之后执行自定义逻辑：{}，{}",beanName,beanName);
        // 返回true，表示继续执行后续的InstantiationAwareBeanPostProcessor
        return true;
    }
    /**
     * 在设置bean属性之前执行自定义逻辑
     * @param bean bean
     * @param beanName beanName
     * @return bean
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        log.info("MyInstantiationAwareBeanPostProcessor 在设置bean属性之前执行自定义逻辑：{}，{}",beanName,beanName);
        return bean;
    }
    /**
     * 在设置bean属性之后执行自定义逻辑
     * @param bean bean
     * @param beanName beanName
     * @return bean
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        log.info("MyInstantiationAwareBeanPostProcessor 在设置bean属性之后执行自定义逻辑：{}，{}",beanName,beanName);
        return bean;
    }
}
