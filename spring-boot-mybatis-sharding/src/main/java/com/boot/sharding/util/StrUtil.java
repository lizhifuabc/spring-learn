package com.boot.sharding.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StrUtil
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class StrUtil {
    /**
     * 获取字符串出现个数
     * @param str
     * @param sign
     * @return
     */
    public static int sumNumber(String str,String sign){
        int count = 0;
        Pattern p = Pattern.compile(sign);
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }
}
