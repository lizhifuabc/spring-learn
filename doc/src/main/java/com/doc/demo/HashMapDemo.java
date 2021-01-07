package com.doc.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap demo
 *
 * @author lizhifu
 * @date 2021/1/7
 */
public class HashMapDemo {
    public static void main(String[] args) {
        //基于哈希表的map接口的实现
        Map hashMap = new HashMap();
        //可以设置null值和null键
        hashMap.put(null,null);
        System.out.println(hashMap.get(null));
        //存放键值对
        hashMap.put("test","test");
        System.out.println(hashMap.get("test"));
    }
}
