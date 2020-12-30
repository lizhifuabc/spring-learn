package com.boot.sharding;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class LocalDateTest {
    @Test
    public void test(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.now().format(fmt));
    }
}
