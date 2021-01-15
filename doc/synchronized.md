# 概念

- 原子性：指一个操作或者多个操作，要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
- 可见性：指多个线程访问一个资源时，该资源的状态、值信息等对于其他线程都是可见的。
- 有序性：指程序执行的顺序按照代码先后执行。
- 可重入性：当一个线程试图操作一个由其他线程持有的对象锁的临界资源时，将会处于阻塞状态，但当一个线程再次请求自己持有对象锁的临界资源时，这种情况属于重入锁。通俗一点讲就是说一个线程拥有了锁仍然还可以重复申请锁。

> synchronized可以修饰静态方法、成员函数，同时还可以直接定义代码块，但是归根结底它上锁的资源只有两类：一个是对象**，一个是**类。

# 初始

当不使用synchronized时：

```java
    static int j = 0;
		@SneakyThrows
    public void increase2(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }

    @Test
    public void increase2() throws Exception {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        Thread thread1 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread2 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread3 = new Thread(() -> synchronizedDemo.increase2());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

多次运行结果：发现实际结果并不能达到预期

```java
09:47:37.825 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:47:37.825 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:47:37.825 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:47:37.829 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完1655
09:47:37.829 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完1018
09:47:37.829 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完2648
2648
```

# synchronized修饰某个实例方法（对象锁）

```java
    static int j = 0;
    @SneakyThrows
    public synchronized void  increase(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }
```

使用同一个对象进行测试：

```java
    @Test
    public void increase() throws Exception {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        Thread thread1 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread2 = new Thread(() -> synchronizedDemo.increase2());
        Thread thread3 = new Thread(() -> synchronizedDemo.increase2());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

运行结果符合预期：

```java
09:51:49.468 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:51:49.471 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完1000
09:51:49.624 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为1000
09:51:49.624 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完2000
09:51:49.777 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为2000
09:51:49.777 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完3000
3000
```

使用不同的对象进行测试：

```java
    @Test
    public void increase3() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

运行结果：(不符合预期)

```java
09:53:42.242 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:53:42.242 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:53:42.246 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完1000
09:53:42.242 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
09:53:42.246 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完2013
09:53:42.246 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完2722
2722
```

分析：

```java
static int j = 0;
```

j的值是属于全局变量，此时虽然在更改方法上面添加了synchronized关键字，但是此时相当于锁记录在这个方法对应的对象上，所以当new了多个实例时，就不能保证结果的准确性。

# synchronized修饰静态方法（类锁）

```java
    @SneakyThrows
    public static synchronized void  increase3(){
        Thread.sleep(150);
        log.info("初始值为{}",j);
        for (int k = 0; k < 1000; k++) {
            j++;
        }
        log.info("执行结束完{}",j);
    }
```

测试代码：

```java
    @Test
    public void increase4() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase3());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase3());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase3());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

运行结果：

```java
10:00:31.242 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
10:00:31.246 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完1000
10:00:31.396 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为1000
10:00:31.396 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完2000
10:00:31.549 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为2000
10:00:31.550 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完3000
3000
```

分析：此时发现不论使用几个对象，总能保持结果的一致性。

# synchronized修饰代码块（类锁）

```java
    @SneakyThrows
    public  void increase4(){
        Thread.sleep(150);
        synchronized(SynchronizedDemo.class){
            log.info("初始值为{}",j);
            for (int k = 0; k < 1000; k++) {
                j++;
            }
            log.info("执行结束完{}",j);
        }
    }
```

测试方法：

```java
    @Test
    public void increase5() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase4());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase4());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase4());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

运行结果：

```java
10:04:23.424 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
10:04:23.427 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完1000
10:04:23.427 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为1000
10:04:23.427 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完2000
10:04:23.427 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为2000
10:04:23.427 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完3000
3000
```

分析：可以保持一致性。

#  synchronized修饰代码块（对象锁）

```java
    @SneakyThrows
    public  void increase5(){
        Thread.sleep(150);
        synchronized(this){
            log.info("初始值为{}",j);
            for (int k = 0; k < 1000; k++) {
                j++;
            }
            log.info("执行结束完{}",j);
        }
    }
```

测试代码：

```java
    @Test
    public void increase6() throws Exception {
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo3 = new SynchronizedDemo();

        Thread thread1 = new Thread(() -> synchronizedDemo1.increase5());
        Thread thread2 = new Thread(() -> synchronizedDemo2.increase5());
        Thread thread3 = new Thread(() -> synchronizedDemo3.increase5());
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(1000);
        System.out.println(SynchronizedDemo.j);
    }
```

运行结果：

```java
10:07:12.399 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 初始值为0
10:07:12.399 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 初始值为0
10:07:12.399 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 初始值为0
10:07:12.407 [Thread-1] INFO com.doc.demo.SynchronizedDemo - 执行结束完2971
10:07:12.407 [Thread-2] INFO com.doc.demo.SynchronizedDemo - 执行结束完2000
10:07:12.407 [Thread-3] INFO com.doc.demo.SynchronizedDemo - 执行结束完1000
2971
```

分析：不能保持预期的结果。

# 原理分析

synchronized有两种形式上锁，一个是对方法上锁，一个是构造同步代码块。他们的底层实现其实都一样，在进入同步代码之前先获取锁，获取到锁之后锁的计数器+1，同步代码执行完锁的计数器-1，如果获取失败就阻塞式等待锁的释放。只是他们在同步块识别方式上有所不一样，从class字节码文件可以表现出来，一个是通过方法flags标志，一个是monitorenter和monitorexit指令操作。

## 同步方法(对象锁)

```java
public class SynchronizedDemo2 {
    static int j = 0;
    public synchronized void increase(){
        j++;
    }
}
```

查看字节码： javap -v SynchronizedDemo2.class (-p显示所有类和成员,-v 输出附加信息)

```java
  public synchronized void increase();
    descriptor: ()V
    flags: (0x0021) ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field j:I
         3: iconst_1
         4: iadd
         5: putstatic     #2                  // Field j:I
         8: return
      LineNumberTable:
        line 12: 0
        line 13: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcom/doc/demo/SynchronizedDemo2;


```

1. ACC_SYNCHRONIZED：标志用来告诉JVM这是一个同步方法，锁的是当前对象

# 同步代码块（类锁）

```java
    public void increase2(){
        synchronized(SynchronizedDemo.class){
            j++;
        }
    }
```

字节码：

```java
  public void increase2();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: ldc           #3                  // class com/doc/demo/SynchronizedDemo
         2: dup
         3: astore_1
         4: monitorenter
         5: getstatic     #2                  // Field j:I
         8: iconst_1
         9: iadd
        10: putstatic     #2                  // Field j:I
        13: aload_1
        14: monitorexit
        15: goto          23
        18: astore_2
        19: aload_1
        20: monitorexit
        21: aload_2
        22: athrow
        23: return
      Exception table:
         from    to  target type
             5    15    18   any
            18    21    18   any
      LineNumberTable:
        line 15: 0
        line 16: 5
        line 17: 13
        line 18: 23
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      24     0  this   Lcom/doc/demo/SynchronizedDemo2;
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 18
          locals = [ class com/doc/demo/SynchronizedDemo2, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4

```

1. 编译时在代码块开始前生成对应的1个 `monitorenter` 指令，代表同步块进入
2. 2个 `monitorexit` 指令，代表同步块退出。
3. 正常退出，得用一个 `monitorexit` 吧，如果中间出现异常，锁会一直无法释放。所以编译器会为同步代码块添加了一个隐式的 `try-finally` 异常处理，在 `finally` 中会调用 `monitorexit` 命令最终释放锁。

# 修饰静态方法（类锁）

```java
    public static synchronized void increase3(){
        j++;
    }
```



```java
  public static synchronized void increase3();
    descriptor: ()V
    flags: (0x0029) ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=0, args_size=0
         0: getstatic     #2                  // Field j:I
         3: iconst_1
         4: iadd
         5: putstatic     #2                  // Field j:I
         8: return
      LineNumberTable:
        line 20: 0
        line 21: 8

  static {};
    descriptor: ()V
    flags: (0x0008) ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: iconst_0
         1: putstatic     #2                  // Field j:I
         4: return
      LineNumberTable:
        line 10: 0
}

```

静态方法相对于普通的方法多了ACC_STATIC，这是两者的区别，这时两者的锁对象是不一样的，普通方法是当前的一个对象，而static方法则是字节码对象