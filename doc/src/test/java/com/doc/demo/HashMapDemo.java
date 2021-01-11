package com.doc.demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HashMap demo
 *
 * @author lizhifu
 * @date 2021/1/7
 */
public class HashMapDemo {
    public static final int MAXIMUM_CAPACITY = 1 << 30;
    @Test
    public void cal(){
        //两个二进制对应位为0时该位为0，否则为1
        int n = 1 << 5; // 0000 0101
        System.out.println("二进制输出"+Integer.toBinaryString(n));
        int b = n >>> 2;
        System.out.println("二进制输出"+Integer.toBinaryString(b));
        n |= n >>> 1;
        System.out.println("二进制输出"+Integer.toBinaryString(n));

        int a = 5; // 0000 0101
        int c = 3; // 0000 0011
//        a |= c; // 0000 00111
        a = a | c;
        System.out.println(a);
    }
    @Test
    public void tableSizeFor(){
        int cap = 16;
        System.out.println("n二进制输出"+Integer.toBinaryString(cap));
        //初始容量-1
        int n = cap-1;
        System.out.println("n-1二进制输出"+Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(n);
        System.out.println("二进制输出"+Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n);
        System.out.println("二进制输出"+Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n);
        System.out.println("二进制输出"+Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n);
        System.out.println("二进制输出"+Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n);
        System.out.println("16:二进制输出"+Integer.toBinaryString(n));
        System.out.println( (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
        System.out.println("二进制输出"+Integer.toBinaryString(n));
    }

    @Test
    public void init() {
        //指定初始容量
        Map hashMap = new HashMap(16);
        System.out.println(hashMap.size());
        System.out.println(1 << 30);
        System.out.println(1 << 31-1);
        System.out.println(Integer.MAX_VALUE);
    }
    @Test
    public void init2() {
        int mapSize = 13;
        System.out.println(((float)mapSize / 0.75f));
        float ft = ((float)mapSize / 0.75f) + 1.0F;
        System.out.println(ft);
    }
    @Test
    public void newCap () {
        HashMap<String,Integer> hashMap = new HashMap();
        hashMap.put("1",2);
        hashMap.put("2",2);
        hashMap.put("3",2);
        hashMap.put("4",2);
        String key = "14";
        int newCap = 16;
        System.out.println("hash:"+Integer.toBinaryString(hash(key)));
        System.out.println("newCap二进制输出"+Integer.toBinaryString(newCap));
        System.out.println("newCap二进制输出"+Integer.toBinaryString(2*newCap));
        System.out.println("newCap二进制输出"+Integer.toBinaryString(2*newCap-1));
        System.out.println("newCap二进制输出"+Integer.toBinaryString(newCap-1));
        System.out.println(hash(key) & (newCap-1));
        System.out.println(Integer.toBinaryString(hash(key) & (newCap-1)));
        System.out.println(hash(key) & (newCap));
    }
    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void hashCodeTest() {
        //指定初始容量
        Map hashMap = new HashMap(16);
        String key = "123";
        String value = "32";
        System.out.println(Objects.hashCode(key));
        System.out.println(Objects.hashCode(value));
        System.out.println(Objects.hashCode(key) ^ Objects.hashCode(value));
    }
    public static void main(String[] args) {
        //基于哈希表的map接口的实现
        HashMap hashMap = new HashMap();
        //可以设置null值和null键
        hashMap.put(null,null);
        System.out.println(hashMap.get(null));
        //存放键值对
        hashMap.put("test","test");
        System.out.println(hashMap.get("test"));
    }
}
