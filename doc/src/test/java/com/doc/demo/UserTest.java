package com.doc.demo;

import org.junit.jupiter.api.Test;

/**
 * User
 *
 * @author lizhifu
 * @date 2021/1/8
 */
public class UserTest {
    @Test
    public void test(){
        User user1 = new User();
        user1.setName("li");
        User user2 = new User();
        user2.setName("li");
        System.out.println("user1 hashcode:"+user1.hashCode());
        System.out.println("user2 hashcode:"+user2.hashCode());
        System.out.println(user1.equals(user2));
    }
}
