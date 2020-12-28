package com.design.adapter.objecttype;

import com.design.adapter.classtype.AC220V;
import com.design.adapter.classtype.DC5V;

/**
 * 适配器角色:对象适配器
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Adapter implements DC5V {
    private AC220V ac220V;
    public Adapter(AC220V ac220V) {
        this.ac220V = ac220V;
    }
    @Override
    public int dc5v() {
        int output220v = ac220V.output220v();
        return (output220v / 44);
    }
}
