package com.boot.lombok.example;

import java.util.concurrent.atomic.AtomicReference;

/**
 * GetterLazyExample
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class GetterLazyExampleOutLombok {
    private final AtomicReference<Object> cached = new AtomicReference();

    public double[] getCached() {
        java.lang.Object value = this.cached.get();
        if (value == null) {
            synchronized(this.cached) {
                value = this.cached.get();
                if (value == null) {
                    final double[] actualValue = expensive();
                    value = actualValue == null ? this.cached : actualValue;
                    this.cached.set(value);
                }
            }
        }
        return (double[])(value == this.cached ? null : value);
    }

    private double[] expensive() {
        double[] result = new double[1000000];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.asin(i);
        }
        return result;
    }
    public static void main(String[] args) {
        //使用Double Check Lock 样板代码对属性进行懒加载
        GetterLazyExampleOutLombok example = new GetterLazyExampleOutLombok();
        System.out.println(example.getCached().length);
    }
}
