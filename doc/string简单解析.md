

# String简单解析
### 演示代码1

```java
public class StringDemo {
    public static void main(String[] args) {
        String str1 = "ab";
        String str2 = "a" + "b";
    }
}
```

1. str1常量池内创建ab

2. "a" + "b"在编译之后也是ab

   ```java
     public static main([Ljava/lang/String;)V
      L0
       LINENUMBER 11 L0
       LDC "ab"
       ASTORE 1
      L1
       LINENUMBER 12 L1
       LDC "ab"
       ASTORE 2
   ......................                     
   ```
### 演示代码2

```java
    public static void test1(){
        String str1 = "ab";
        String str2 = new String("ab");
    }
```

1. 常量池创建ab
2. 堆中创建一个String对象

```java
public static test1()V
   L0
    LINENUMBER 18 L0
    LDC "ab"
    ASTORE 0
   L1
    LINENUMBER 19 L1
    NEW java/lang/String --- 创建新的对象
    DUP
    LDC "ab"
    INVOKESPECIAL java/lang/String.<init> (Ljava/lang/String;)V
    ASTORE 1
   L2
```
