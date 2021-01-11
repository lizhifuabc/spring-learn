# resize() 扩容

## 基础知识

- << : 左移运算符，num << 1,相当于num乘以2

- “与、与位”&可以用作位运算符，当&两边的表达式不是Boolean类型的时候，&表示按位操作。如果两个数的二进制，相同位数都是1，则该位结果是1，否则是0(有0则为0)

  ```java
      @Test
      public void test3(){
          int a = 14;
          int b = 13;
          System.out.println("a的二进制"+Integer.toBinaryString(a));
          System.out.println("b的二进制"+Integer.toBinaryString(b));
          System.out.println("二进制结果:"+Integer.toBinaryString((a&b)));
          System.out.println("十进制结果:"+(a&b));
      }
  ```

  运行结果：

  a的二进制  1110
  b的二进制  1101
  二进制结果:1100
  十进制结果:12

## 容量扩容(2倍)

```java
    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     *
     * @return the table
     */
    final Node<K,V>[] resize() {
        //当前table值 lodTab
        Node<K,V>[] oldTab = table;
        //扩容前长度
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //扩容前扩容临界值
        int oldThr = threshold;
        //新的扩容临界值0和扩容初始容量
        int newCap, newThr = 0;
        //如果扩容前长度>0
        if (oldCap > 0) {
            //如果原容量已经达到最大容量了，无法进行扩容，直接返回
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //新的扩容临界值=扩容前扩容临界值*2
            //扩容前长度>=默认的总容量
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                //新的扩容临界值=扩容前扩容临界值*2即两倍扩容
                newThr = oldThr << 1; // double threshold
        }
         /**
        * 从构造方法我们可以知道
        * 如果没有指定initialCapacity, 则不会给threshold赋值, 该值被初始化为0
    	  * 如果指定了initialCapacity, 该值被初始化initialCapacity的最小的2的次幂
		    * 这里这种情况指的是原table为空，并且在初始化的时候指定了容量，
		    * 则用threshold作为table的实际大小
		    */
        else if (oldThr > 0) // initial capacity was placed in threshold
            //扩容后初始容量=扩容前扩容临界值
            newCap = oldThr;
        //构造方法中没有指定容量，则使用默认值
        else {               
            // zero initial threshold signifies using defaults
            //默认容量
            newCap = DEFAULT_INITIAL_CAPACITY;
            //默认阈值16*0.75
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        // 计算新的resize阈值
        // 计算指定了initialCapacity情况下的新的 threshold
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        //新的table
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //如果老的oldTab里面有数据，则进行数据迁移到新的newTab
        if (oldTab != null) {
            //遍历原始table
            for (int j = 0; j < oldCap; ++j) {
                //当前节点
                Node<K,V> e;
                //table中存放的只是Node的引用
                if ((e = oldTab[j]) != null) {
                    //清除旧表的引用
                    oldTab[j] = null;
                    //没有下一节点,说明e是最后一个节点
                    if (e.next == null)
                        //newTab中进行重定位
                        //e.hash是key的hashcode值
                        //newCap是table的容量值(长度),是2的幂，所以在执行-1操作的时候
                        //进行(与位&)运算
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        //桶中为红黑树，则对树进行拆分
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order 链表结构
                        //loHead:lo链表的头节点 loTail:lo链表尾节点
                        Node<K,V> loHead = null, loTail = null;
                        //hiHead:hi链表的头节点 hiTail:hi链表尾节点
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            //不需要移位操作
                            if ((e.hash & oldCap) == 0) {
                                //lo链表尾节点为空，证明第一次进入
                                if (loTail == null)
                                    //设置lo链表头节点为当前节点
                                    loHead = e;
                                else
                                    //lo链表尾节点不为空，设置lo链表尾节点下一个节点为当前节点
                                    loTail.next = e;
                                //设置当前节点为lo链表尾节点
                                loTail = e;
                            }
                            else {
                                //插入hi链表
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        //如果lo链表非空, 我们就把整个lo链表放到新table的j位置上
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        //如果hi链表非空, 我们就把整个hi链表放到新table的j+oldCap位置上
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

扩容时分为三种情况：

- 第一次扩容(即第一次调用put方法时)

  - 初始化map时指定了初始容量的大小，此时loadFactor和threshold都进行了赋值。在扩容时(table==null)，则指定threshold作为table的实际大小

    ```java
     newCap = oldThr;
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
> 初始化table或者扩容, 实际上都是通过新建一个table来完成，是非常耗时的。

## 元素操作

### 单节点

**e.hash & (newCap - 1)**

把key的hashcode值与table长度-1做位与操作。

newCap在设计之初设定为2的幂，所以在-1时，就形成了“低位掩码”，newCap - 1的高位全0，“与”操作的结果就是散列值的高位全部归零，只保留低位值，用来做数组下标访问

```java
比如长度为16：此时和某散列值做“与”操作，就是截取了最低的四位值
还是存在问题,这样就算散列值分布再松散,要是只取最后几位的话,碰撞也会很严重
00000000 00000000 00000000 00010000(16)
00000000 00000000 00000000 00001111(15)
```

扰动函数：

```java
    static final int hash(Object key) {
        int h;
        //^相同取0，相反取1
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

右位移16位，正好是32位一半，自己的高半区和低半区做异或(相同取0，相反取1)，混合原始hashCode的高位和低位，以此来加大低位的随机性，而且混合后的低位掺杂了高位的部分特征，这样高位的信息也被变相保留下来。

这也很好的解释了为什么hashMap是无序的。

> 综上：
>
> 所以在计算key的hashCode时,用其自身hashCode与其低16位做异或操作，这也就让高位参与到index的计算中来了,即降低了哈希冲突的风险又不会带来太大的性能问题

### 树节点

> JDK1.8中，HashMap采用数组+链表+红黑树实现，当链表长度超过阈值（8）时，将链表转换为红黑树，这样大大减少了查找时间

```java
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
```



```java
        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
            //赋值，((TreeNode<K,V>)e)调用split()方法，所以this是指(TreeNode<K,V>)e对象
            TreeNode<K,V> b = this;
            // Relink into lo and hi lists, preserving order
            //设置低位首节点和低位尾节点
            TreeNode<K,V> loHead = null, loTail = null;
             //设置高位首节点和高位尾节点
            TreeNode<K,V> hiHead = null, hiTail = null;
            //定义两个变量lc和hc，初始值为0，决定了红黑树是否要转回链表
            int lc = 0, hc = 0;
            //从e节点开始对整个红黑树做遍历,相当于do-while循环
            for (TreeNode<K,V> e = b, next; e != null; e = next) {
                //取e的下一节点赋值给next遍历
                next = (TreeNode<K,V>)e.next;
                //取好e的下一节点后，把它赋值为空，方便GC回收
                e.next = null;
                //以下的操作就是做个按位与运算，按照结果拉出两条链表
                //bit:原始table长度
                //与位操作：有0则为0
                //此时元素在新旧table中位置一样
                if ((e.hash & bit) == 0) {
                    //低位尾节点为null的话，说明还没开始遍历这个桶下的链表，就把e赋值给低位首节点
                    if ((e.prev = loTail) == null)
                        //e赋值给低位首节点
                        loHead = e;
                    //否则低位尾节点不为null的话，说明已经在遍历了  
                    else
                        //把低位尾节点的后继节点设置为e节点
                        loTail.next = e;
                    //把e节点赋值给低位尾节点，因为每次e节点都会被赋值成next，而原来的e又被赋值成loTail，通过loTail.next = e，就可以让e的后继节点指向e.next,所以这步加上上一步，就可以形成一个单向链表了
                    loTail = e;
                    ++lc;
                }
                else {
                    if ((e.prev = hiTail) == null)
                        hiHead = e;
                    else
                        hiTail.next = e;
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD)
                    tab[index] = loHead.untreeify(map);
                else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                        loHead.treeify(tab);
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD)
                    tab[index + bit] = hiHead.untreeify(map);
                else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }
```

- (e.hash & oldCap) == 0(&有0则为0)

  首先oldCap是2的幂，所以二进制一定为100..000这种格式，所以(e.hash & oldCap)如果是0，那么说明oldCap对应二进制位置1的位置e.hash一定为0。同理来讲新的newCap是2倍的扩容，所说(e.hash & oldCap)结果保持不变，

  关联：put数据时对应的位置

  ```java
  newTab[e.hash & (newCap - 1)] = e;
  
  e.hash & (oldCap - 1)
    
  e.hash & (2oldCap - 1)
   
  是一致的。
  ```

  即此时元素是第一个node，即下标index == 0