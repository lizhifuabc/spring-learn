package com.design.adapter.classtype;

/**
 * 适配器角色
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Adapter extends AC220V implements DC5V{
    @Override
    public int dc5v() {
        System.out.println("适配器工作开始适配电压");
        int output220v = output220v();
        int dst = output220v / 44;
        System.out.println("适配完成后输出电压：" + dst);
        return dst;
    }
}
