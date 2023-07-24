package com.boot.jpa;

import com.boot.jpa.domain.Child;
import com.boot.jpa.domain.Parent;
import com.boot.jpa.repository.ChildRepository;
import com.boot.jpa.repository.ParentRepository;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 父对象
 *
 * @author lizhifu
 * @since 2023/7/24
 */
@SpringBootTest
public class ParentChildTest {
    @Resource
    private ParentRepository parentRepository;
    @Resource
    private ChildRepository childRepository;
    @Test
    public void test() {
        // 保存父对象
        Parent parent = new Parent();
        parent.setName("parent");
        parentRepository.save(parent);

        // 保存子对象
        Child child = new Child();
        child.setName("child");
        child.setParent(parent);
        childRepository.save(child);
    }
    @Test
    public void test2() {
        // 保存父对象
        Parent parent = new Parent();
        parent.setName("parent");

        // 保存子对象
        Child child = new Child();
        child.setName("child");
        child.setParent(parent);

        parent.setChildren(List.of(child));
        parentRepository.save(parent);
    }
}
