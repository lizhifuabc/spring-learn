# 简介

hashmap扩容主要分为两部分：

1. 扩大容量并设置`threshold`
2. 对table进行操作

这里先讲第一步操作：

# 扩容

源码：

```java
    final Node<K,V>[] resize() {
        //旧table
        Node<K,V>[] oldTab = table;
        //旧table长度
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //旧table阈值
        int oldThr = threshold;
        //初始化新的table长度和阈值
        int newCap, newThr = 0;
        if (oldCap > 0) {
            //容量大于最大限制，直接返回
            if (oldCap >= MAXIMUM_CAPACITY) {
                //阈值也设置为最大
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //扩大阈值为2倍 扩大容量2倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        //初始化了阈值，但是此时table没有数据，直接设置新的table长度为旧的阈值
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {
            //使用了默认的构造方法
            // zero initial threshold signifies using defaults、
            // 默认容量=16
            newCap = DEFAULT_INITIAL_CAPACITY;
            //默认阈值=16*0.75
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        //初始化了阈值，但是此时table没有数据，直接设置新的table长度为旧的阈值
        //此时重新计算新的阈值newThr
        //newThr = oldThr * 0.75
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        //设置新的阈值
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //其他暂时省略............................
    }
```

扩容时分为三种情况：

- 第一次扩容(即第一次调用put方法时)

  - 初始化map时指定了初始容量的大小，此时loadFactor和threshold都进行了赋值。在扩容时(table==null)，则指定threshold作为table的实际大小

    ```java
    newCap = oldThr;
    //重新计算新的阈值
    float ft = (float)newCap * loadFactor;
    newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
              (int)ft : Integer.MAX_VALUE);
    ```

  - 初始化map时使用默认构造函数，此时loadFactor进行了赋值，threshold和table都没有进行初始化。在扩容时(table==null)，指定table容量为默认值16，threshold为默认值16*0.75

    ```java
    newCap = DEFAULT_INITIAL_CAPACITY;
    newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    ```

- 非第一次扩容时

  - 达到了hashmap规定的最大容量，无法进行扩容，直接返回

    ```java
    if (oldCap >= MAXIMUM_CAPACITY) {
    	threshold = Integer.MAX_VALUE;
    	return oldTab;
    }
    ```

  - 正常情况下设置新容量为旧容量的两倍，threshold也变为原来的两倍

    ```java
     else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
     oldCap >= DEFAULT_INITIAL_CAPACITY)
     newThr = oldThr << 1; // double threshold
    ```

> 综上：
>
> 如果构造函数没有指定initialCapacity, 则table大小为16，threshold为12
>
> 如果构造函数指定了initialCapacity, 则table大小为threshold
>