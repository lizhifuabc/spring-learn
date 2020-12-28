package com.design.adapter.interfacetype;

import com.design.adapter.classtype.DC5V;

/**
 * 适配器角色
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class Adapter implements DC5V {
    private ACV acv;

    public Adapter(AC220V ac220V) {
        this.acv = ac220V;
    }

    public Adapter(AC110V ac110V) {
        this.acv = ac110V;
    }
    @Override
    public int dc5v() {
        int ac = 0;
        if (acv != null) {
            ac = acv.output();
        }
        int sta = ac / 5;
        return (ac / sta);
    }
}
