package com.boot.api;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则测试
 *
 * @author lizhifu
 * @date 2020/12/24
 */
public class RegTest {
    public static void main(String[] args) {
        boolean a = isNumber("0.111");
        System.out.println(a);
    }
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }
}
