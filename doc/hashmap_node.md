# Node节点

## 基础知识

- 十进制转二进制：**除2取余，逆序排列**

- 异或运算符^

>相同取0，不同取1。即两操作数相同时，互相抵消。

```java
    @Test
    public void test(){
        int a = 15;
        int b = 13;
        System.out.println("a的二进制"+Integer.toBinaryString(a));
        System.out.println("b的二进制"+Integer.toBinaryString(b));
        System.out.println("二进制结果:"+Integer.toBinaryString((a^b)));
        System.out.println("十进制结果:"+(a^b));
    }
```

运行结果：

a的二进制  1111
b的二进制  1101
二进制结果:0010
十进制结果:2

算法题：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

## 源码

```java
    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        //哈希值，存放元素到hashmap中时用来与其他元素hash值比较
        final int hash;
        //键
        final K key;
        //值
        V value;
        //指向下一个节点
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        // 实现接口定义的方法，且该方法不可被重写
        public final K getKey()        { return key; }
        // 实现接口定义的方法，且该方法不可被重写
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }
        // 重写hashCode()方法
        //^运算符跟 | 类似，但有一点不同的是 如果两个操作位都为1的话，结果产生0
        //运算规则：0^0=0；  0^1=1；  1^0=1；   1^1=0；
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        // 重写 equals() 方法
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
```

- 重写equals，必须重写hashcode。
- 如果equals为true的两个对象，hashcode也必须相等。
- 如果equals为false的两个对象，hashcode可以相等，也可以不相等。

在这里使使用^异或操作，可以尽可能的使用到key和value的每一位数据，保证在两个Node比较(equal)不相等的情况下尽量生成不同的hashCode,易于在散列表中查找。

> 为什么要重写hashcode：
>
> 默认的equals 判断的是两个对象的引用指向的是不是同一个对象，如果不只重写equals，此时会出现equal相等但是hashcode不一致的情况，重写hashcode可以避免这种情况。