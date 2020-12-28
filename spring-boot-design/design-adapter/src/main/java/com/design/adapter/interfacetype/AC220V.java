package com.design.adapter.interfacetype;

/**
 * 源角色--具体的220V电源
 *
 * @author lizhifu
 * @date 2020/12/28
 */
public class AC220V extends ACV {
    @Override
    public int output() {
        return 220;
    }
}
