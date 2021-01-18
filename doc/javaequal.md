# 基础

1. 运行时常量池：

   方法区中存放：类信息、常量、静态变量、即时编译器编译后的代码。常量就存放在运行时常量池中。

   当类被 Java 虚拟机加载后， .class 文件中的常量就存放在方法区的运行时常量池中。而且在运行期间，可以向常量池中添加新的常量。如 String 类的 `intern()` 方法就能在运行期间向常量池中添加字符串常量。

2. 堆是用来存放对象的内存空间，几乎所有的对象都存储在堆中。

# equals()

equals()是Object类的方法，而Object类又是所有类的祖宗（父类），所有的引用类型都有equals()方法。

可以查看各个类的重写方法，都是比较值而不是内存地址。

String：

```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

# ==

概念：比较它两边的值是否相同

1. 对于对象引用类型：“==”比较的是对象的内存地址。

   ```java
       @Test
       public void test(){
           String a = "ab";
           String b = "a"+"b";
           System.out.println(a == b);
       }
   ```

   运行结果：true。

   1. String a = "ab";分配在常量池中。
   2. String b = "a"+"b";中的"a"和"b"也存在常量池中，执行加法时会在常量池中寻找是否存在"ab”，此时a和b同时指向"ab"；

   ```java
       @Test
       public void test2(){
           String a = "ab";
           String b = new String("ab");
           System.out.println(a == b);
       }
   ```

   运行结果：false。

   1. String b = new String("ab");分配在堆中，而a存在于常量池中，故false；

2. 对于基本类型数据，其实比较的是它的值。

   > 浮点型2种--float、double
   >
   > 字符型：char
   >
   > 布尔型：boolean
   >
   > 整型4种--byte、short、int、long