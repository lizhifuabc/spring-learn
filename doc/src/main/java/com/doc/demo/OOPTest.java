package com.doc.demo;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class OOPTest {
    private String name;
    private int gender;
    private int age;
    public String getGender() {
        return gender == 0 ? "男" : "女";
    }
    public void canWork() {
        if (18 <= age && age <= 50) {
            System.out.println(name + " 正常上班");
        } else {
            System.out.println(name + " 该退休了");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
