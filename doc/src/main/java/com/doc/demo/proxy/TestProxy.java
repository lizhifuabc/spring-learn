package com.doc.demo.proxy;

/**
 * TestProxy
 *
 * @author lizhifu
 * @date 2021/1/21
 */
public class TestProxy {
    public static void main(String args[])
    {
        //项目的根目录生成一个$Proxy0.class
        //生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyHandler proxy = new ProxyHandler();
        //绑定该类实现的所有接口
        Subject sub = (Subject) proxy.bind(new RealSubject());
        System.out.println(sub.getClass().toString());
        sub.doSomething();
    }
}
