package com.design.singleton;

/**
 * TODO
 *
 * @author Marz
 * @date 2021/7/22
 */
public class SingletonTest {

    public static void main(String[] args) {

        // 声明Singleton类型的引用指向Singleton类型的对象
        // Singleton singleton = new Singleton();
        // Singleton singleton2 = new Singleton();
        // System.out.println(singleton == singleton2); // false

        /*SingletonHungry singleton = SingletonHungry.singleton;
        SingletonHungry singletonHungry = SingletonHungry.singleton;
        System.out.println(singleton == singletonHungry); // true

        SingletonHungry.singleton = null;
        SingletonHungry singletonHungry1 = SingletonHungry.singleton;
        System.out.println("singletonHungry1 = " + singletonHungry1);*/

        SingletonHungry instance = SingletonHungry.getInstance();
        SingletonHungry instance1 = SingletonHungry.getInstance();
        System.out.println(instance == instance1);

        SingletonHungry[] array = new SingletonHungry[2];
        new Thread() {
            @Override
            public void run() {
                array[0] = SingletonHungry.getInstance();
                System.out.println("0:"+array[0]);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                array[1] = SingletonHungry.getInstance();
                System.out.println("1:"+array[1]);
            }
        }.start();
        System.out.println(array[1]);
        System.out.println(array[0] == array[1]); // true
    }
}
