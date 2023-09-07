package com.doc.demo.beanutils;

import org.springframework.beans.BeanUtils;

/**
 * 类型不匹配
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class BeanUtilsDemo {
    public static void main(String[] args) {
        Source source = new Source();
        source.setAge("1");

        Target target = new Target();
        BeanUtils.copyProperties(source,target);
        System.out.println(target.getAge());
    }

    private static class Source{
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
    private static class Target{
        private Long age;

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }
    }
}
