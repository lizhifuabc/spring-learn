package com.boot.log.util;

/**
 * 数组
 *
 * @author lizhifu
 * @date 2020/12/1
 */
public class ArrayUtil {
    /**
     * 数组是否为空
     *
     * @param <T>   数组元素类型
     * @param array 数组
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
