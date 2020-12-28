package com.design.adapter.interfacetype;

import com.design.adapter.classtype.DC5V;

/**
 * Test
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Test {
    public static void main(String[] args) {
        DC5V dc5V = new Adapter(new AC220V());
        int dc = dc5V.dc5v();
        System.out.println("输入的电压为：" + new AC220V().output() + " 伏...");
        System.out.println("转换后的电压为：" + dc + " 伏...");
    }
}
