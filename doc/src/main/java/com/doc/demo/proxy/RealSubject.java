package com.doc.demo.proxy;

/**
 * RealSubject
 *
 * @author lizhifu
 * @date 2021/1/21
 */
public class RealSubject implements Subject{
    @Override
    public void doSomething()
    {
        System.out.println( "call doSomething()" );
    }
}
