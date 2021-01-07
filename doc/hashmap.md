# HashMap 简介
HashMap 主要用来存放键值对，基于哈希表的Map接口实现，并允许使用 null 值和 null 键，是常用的Java集合之一。

```java
//基于哈希表的map接口的实现
Map hashMap = new HashMap();
//可以设置null值和null键
hashMap.put(null,null);
System.out.println(hashMap.get(null));
//存放键值对
hashMap.put("test","test");
System.out.println(hashMap.get("test"));
```

# 构造函数

HashMap一共有四个构造方法：

## 默认构造函数

```java
/**
* Constructs an empty <tt>HashMap</tt> with the default initial capacity
* (16) and the default load factor (0.75).
* 构造一个空的HashMap,使用默认的容量16和默认的加载因子0.75
*/
public HashMap() {
	this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
```

> 解释：默认容量为 16，负载因子为 0.75。当16 * 0.75 = 12时进行扩容。

