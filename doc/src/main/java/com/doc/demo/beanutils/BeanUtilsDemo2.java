package com.doc.demo.beanutils;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 类型不匹配
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class BeanUtilsDemo2 {
    public static void main(String[] args) {
        User user = new User();
        user.setName("lizhifu");

        Source source = new Source();
        source.setAge("1");
        source.setUser(user);

        Target target = new Target();
        BeanUtils.copyProperties(source,target);
        // 更改原始对象的值
        user.setName("lizhifu2");
        source.setAge("2");

        System.out.println(target.getUser().getName());
        System.out.println(target.getAge());
    }
    @Data
    private static class Source {
        private String age;
        private User user;
    }
    @Data
    private static class Target {
        private String age;
        private User user;
    }
    @Data
    private static class User {
        private String name;
    }
}
