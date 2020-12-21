package com.boot.hot.enums;

/**
 * 资金变动方向
 *
 * @author lizhifu
 * @date 2020/12/18
 */
public enum DirectionType {
    ADD(0),
    RDDUCE(1);

    private final Integer name;

    private DirectionType(Integer name)
    {
        this.name = name;
    }
    public Integer getName() {
        return name;
    }
}
