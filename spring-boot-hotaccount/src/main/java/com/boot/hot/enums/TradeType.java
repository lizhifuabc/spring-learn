package com.boot.hot.enums;

/**
 * 交易类型
 *
 * @author lizhifu
 * @date 2020/12/18
 */
public enum TradeType {
    TRANS(0),
    PAGE(1),
    ORDER(2);

    private final Integer name;

    private TradeType(Integer name)
    {
        this.name = name;
    }
    public Integer getName() {
        return name;
    }
}
