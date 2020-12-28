package com.design.adapter.objecttype;

import com.design.adapter.classtype.AC220V;
import com.design.adapter.classtype.DC5V;

/**
 * 对象适配器
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Test {
    public static void main(String[] args) {
        DC5V dc5V =  new Adapter(new AC220V());
        int dc5 = dc5V.dc5v();
        System.out.println("输入的电压为：" + new AC220V().output220v() + " 伏...");
        System.out.println("转换后的电压为：" + dc5 + " 伏...");
    }
}
