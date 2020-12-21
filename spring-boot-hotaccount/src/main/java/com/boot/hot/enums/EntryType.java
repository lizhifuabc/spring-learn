package com.boot.hot.enums;

/**
 * 是否入账
 *
 * @author lizhifu
 * @date 2020/12/18
 */
public enum EntryType {
    YES(0),
    NO(1);

    private final Integer name;

    private EntryType(Integer name)
    {
        this.name = name;
    }
    public Integer getName() {
        return name;
    }
}
