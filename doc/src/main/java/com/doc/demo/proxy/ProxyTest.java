package com.doc.demo.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 代理测试
 *
 * @author lizhifu
 * @date 2021/1/21
 */
public class ProxyTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clz = Class.forName("com.doc.demo.proxy.ProxyTest2");
        Object o = clz.newInstance();
        Method m = clz.getMethod("test", String.class,Integer.class);
        for (int i = 0; i < 16; i++) {
            m.invoke(o, "huige",i);
        }
    }
}
