package com.doc.demo;

import com.doc.demo.type.StatusType;
import org.junit.jupiter.api.Test;

/**
 * StatusType
 *
 * @author lizhifu
 * @date 2021/4/8
 */
public class StatusTypeTest {
    public static void main(String[] args) {
        System.out.println(StatusType.SUCCESS.name());
        System.out.println(StatusType.SUCCESS);
        System.out.println(StatusType.SUCCESS.name().getClass());
        System.out.println(StatusType.SUCCESS.getClass());
    }
    @Test
    public void test(){
        String status = "SUCCESS";
        System.out.println(StatusType.SUCCESS.equals(status));
        System.out.println(StatusType.SUCCESS.name().equals(status));
    }
}
