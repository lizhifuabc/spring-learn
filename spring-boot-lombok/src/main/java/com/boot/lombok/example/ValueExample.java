package com.boot.lombok.example;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.Value;
import lombok.With;
import lombok.experimental.NonFinal;

/**
 * https://projectlombok.org/features/Value
 * 类声明为不可变的，声明后此类相当于final类，无法被继承，其属性也会变成final属性。
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Value
public class ValueExample {
    String name;
    @With(AccessLevel.PACKAGE)
    @NonFinal
    int age;
    double score;
    protected String[] tags;

    @ToString(includeFieldNames=true)
    @Value(staticConstructor="of")
    public static class Exercise<T> {
        String name;
        T value;
    }
    public static void main(String[] args) {
        //只能使用全参构造器
    }
}
