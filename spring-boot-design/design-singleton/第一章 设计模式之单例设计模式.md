# 第一章 设计模式之单例设计模式

## 1.1 常用的设计原则

### 1.1.1 软件开发的流程

* 需求分析文档、概要设计文档、详细设计文档、编码和测试、安装和调试、维护和升级

### 1.1.2 常用的设计原则

* 开闭原则（Open Close Principle）
  对扩展开放对修改关闭，为了使程序的扩展性好，易于维护和升级。

* 里氏代换原则（Liskov Substitution Principle）
  任何基类可以出现的地方，子类一定可以出现，多使用多态的方式。

* 依赖倒转原则（Dependence Inversion Principle）
  尽量多依赖于抽象类或接口而不是具体实现类，对子类具有强制性和规范性

* 接口隔离原则（Interface Segregation Principle）
  尽量多使用小接口而不是大接口，避免接口的污染，降低类之间耦合度。

* 迪米特法则（最少知道原则）（Demeter Principle）
  一个实体应当尽量少与其他实体之间发生相互作用，使系统功能模块相对独立。

  高内聚，低耦合。

* 合成复用原则（Composite Reuse Principle）
  尽量多使用合成/聚合的方式，而不是继承的方式。



## 1.2 常用的设计模式

### 1.2.1 基本概念

* 设计模式（Design pattern）是一套被反复使用、多数人知晓的、经过分类编目的、代码设计经验的总结。
* 设计模式就是一种用于固定场合的固定套路。
* 使用设计模式是为了可重用代码、让代码更容易被他人理解、保证代码可靠性。

### 1.2.2 基本分类

* 创建型模式（5种） - 单例设计模式、工厂方法模式、抽象工厂模式、建造者模式、原型模式。
* 结构型模式（7种） - 装饰器模式、代理模式、适配器模式、外观模式、桥接模式、组合模式、享元模式。
* 行为型模式（11种）- 模板设计模式、策略模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。



## 1.3 单例设计模式详解

### 1.3.1 基本概念

* 单例设计模式主要描述一个类对外提供且只提供一个对象，并且该类自己负责对象创建的模式。

### 1.3.2 实现方式

* 单例设计模式的实现方式主要有5种：饿汉式、懒汉式、双重锁校验式、静态内部类式、枚举式。

### 1.3.3 饿汉式详解

#### （1）类图结构

#### （2）代码实现

```java
public class SingletonHungry {
    /**
     * 2.声明本类类型的引用指向本类类型的对象并使用private static关键字共同修饰
     *   程序启动时创建实例，借助static关键字的特殊性
     */
    private static SingletonHungry instance = new SingletonHungry();

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private SingletonHungry() {}

    /**
     * 3.提供公有的get方法负责将上述对象返回出去，使用public static关键字共同修饰
     *   保障多线程的安全性
     * @return
     */
    public static SingletonHungry getInstance() {
        return instance;
    }
}
```

#### （3）主要优点

* 不需要加锁就已经保证了多线程安全，执行效率比较高。

#### （4）主要缺点

* 类加载时就已经创建了对象，容易产生垃圾对象且浪费内存空间，没有达到延迟加载的效果。

### 1.3.4 懒汉式详解

#### （1）类图结构

#### （2）代码实现

```java
public class SingletonLazy {
    /**
     * 2.声明本类类型的引用指向本类类型的对象并使用private static关键字共同修饰
     */
    private static SingletonLazy instance;

    /**
     * 1.私有化构造方法，使用private关键字修饰，此时不允许外部直接创建实例
     */
    private SingletonLazy() {}

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     * 版本一：不保障多线程的安全性
     * @return
     */
    public static SingletonLazy getInstance() {
        if(null == instance) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    /**
     * 3.提供公有的get方法负责创建对象并返回出去，使用public static关键字共同修饰
     *   版本二：保障多线程的安全性
     * @return
     */
    public static synchronized SingletonLazy getInstance2() {
        if(null == instance){
            instance = new SingletonLazy();
        }
        return instance;
    }
```

#### （3）主要优点

* 只有调用方法才会创建对象，避免垃圾对象的产生和内存空间的浪费，达到了延迟加载的效果。

#### （4）主要缺点

* 需要加锁才能保证多线程安全，加锁导致执行效率比较低。