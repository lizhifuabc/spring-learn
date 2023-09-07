package com.doc.demo;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class OOPChildTest extends OOPTest{
    @Override
    public void canWork() {
        if (18 <= getAge() && getAge() <= 50) {
            System.out.println(getName() + " 正常上班");
        } else {
            System.out.println(getName() + " 退什么休，我还能干");
        }
    }
}
