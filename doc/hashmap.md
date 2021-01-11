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

# 属性

```java
    /**
     * The default initial capacity - MUST be a power of two.
     * 默认的初始容量是16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     * 最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * 填充因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     * 当桶(bucket)上的结点数大于这个值时会转成红黑树
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     * 当桶(bucket)上的结点数小于这个值时树转链表
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     * 桶中结构转化为红黑树对应的table的最小大小
     */
    static final int MIN_TREEIFY_CAPACITY = 64;
    /* ---------------- Fields -------------- */

    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     * 存储元素的数组，总是2的幂次倍
     */
    transient Node<K,V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     * 存放具体元素
     */
    transient Set<Map.Entry<K,V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     * 存放元素的个数，注意这个不等于数组的长度。
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     * 每次扩容和更改map结构的计数器
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    // threshold = capacity * loadFactor
    //当Size>=threshold的时候，那么就要考虑对数组的扩增了。衡量数组是否需要扩增的一个标准。
    int threshold;
    /**
     * The load factor for the hash table.
     * 加载因子
     * @serial
     */
    final float loadFactor;
```

# 构造函数

基础知识(很重要)：

基本类型：int 二进制位数：32，取值范围-2^31—2^31-1
包装类：java.lang.Integer

二进制：1表示负数，0表示正数

最小值：Integer.MIN_VALUE= -2147483648 （-2的31次方） 10000000000000000000000000000000
最大值：Integer.MAX_VALUE= 2147483647 （2的31次方-1）01111111111111111111111111111111

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

```java
/**
* The load factor used when none specified in constructor.
* 当没有特殊指定时，使用的默认加载因子
*/
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

```java
/**
* The default initial capacity - MUST be a power of two.
* 默认的初始容量，必须是2的幂
*/
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```

## 指定“容量大小”的构造函数(同下)

```java
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
```

## 指定“容量大小”和“加载因子”的构造函数

```java
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public HashMap(int initialCapacity, float loadFactor) {
        //初始容量不能<0
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
       //最大不能超过MAXIMUM_CAPACITY = 1<<30(2的30幂)
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
       //必须是Float
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        //计算临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
        this.threshold = tableSizeFor(initialCapacity);
    }
```

MAXIMUM_CAPACITY：1 << 30(必须是2的幂，所以不能是Integer.MAX_VALUE)，容量最大也就是32bit的正数

> 2的30幂：01000000000000000000000000000000
>
> 二进制中最左的一位是标识正负之分，所以只能左移30位
>
> 核心问题应该是为什么是2的幂，下面再解释

loadFactor：加载因子：float

> 扩容规则是，达到默认容量*加载因子时进行扩容，所以必须是float

threshold：扩容临界值。计算临界值：

```java
    /**
     * Returns a power of two size for the given target capacity.
     * 返回大于输入参数且最近的2的整数次幂的数,如果就是2的幂，则返回的还是这个数
     * >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
     * a|=b(或者a=a|b)两个二进制对应位为0时该位为0，否则为1
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

其实说了这么多，核心的作用就一个，就是让二进制所有的位数都变成1。

分析，极端情况(但是实际的初始化时，已经判断了n的最大值是n=1<<30，此时再进行n-1在进行运算)

最大值n=1<<30:初始化时代码判断n的最大值就是MAXIMUM_CAPACITY

> ```java
> 01 00000 00000 00000 00000 00000 00000 (n)   
> 01 10000 00000 00000 00000 00000 00000 (n |= n >>> 1)    
> 01 11100 00000 00000 00000 00000 00000 (n |= n >>> 2)    
> 01 11111 11000 00000 00000 00000 00000 (n |= n >>> 4)    
> 01 11111 11111 11111 00000 00000 00000 (n |= n >>> 8)    
> 01 11111 11111 11111 11111 11111 11111 (n |= n >>> 16)
> 
> 由于n是正数，所以最多有31个1，所以位移31次就可以了。
> ```

- 为什么要进行cap-1的操作，主要是为了防止cap已经是2的幂，如果此时进行移位，最终的结果就变成了cap的两倍，2*cap。其实很好理解，如果n是2的幂，那么二进制指定是存在一个1的情况，此时通过移位操作，等于是最高位之后的0全部变成了1，那么久等于是扩大了2倍

- Jdk1.8之后的优化:此时看起来就更明朗了

  ```java
      /**
       * Returns a power of two size for the given target capacity.
       */
      static final int tableSizeFor(int cap) {
          //直接通过最高位前面的0的个数直接移动，避免了多次移动
          //11111111111111111111111111111111(-1的二进制)
          int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
          return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
      }
  ```

  ## 包含另一个“Map”的构造函数

```java
    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        //默认加载因子
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
```

putMapEntries方法：

```java
    /**
     * Implements Map.putAll and Map constructor.
     *
     * @param m the map
     * @param evict false when initially constructing this map, else
     * true (relayed to method afterNodeInsertion).
     */
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        //原始map大小
        int s = m.size();
        //没有内容就什么都不做了
        if (s > 0) {
            // table是否已经初始化
            if (table == null) { // pre-size
                //未初始化，s为m的实际元素个数
                //获取扩容的阈值threshold
                float ft = ((float)s / loadFactor) + 1.0F;
                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                         (int)ft : MAXIMUM_CAPACITY);
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }
            //已初始化，并且m元素个数大于阈值，进行扩容处理
            else if (s > threshold)
                //扩容
                resize();
            // 将m中的所有元素添加至HashMap中
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                //存放值
                putVal(hash(key), key, value, false, evict);
            }
        }
    }
```
