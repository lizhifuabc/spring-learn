package com.boot.jpa;

import com.boot.jpa.domain.QDslDemo;
import com.boot.jpa.repository.DslDemoQuerydslRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DslDemoQuerydslRepository
 *
 * @author lizhifu
 * @since 2023/7/31
 */
@SpringBootTest
public class QuerydslTest {
    @Resource
    private DslDemoQuerydslRepository dslDemoQuerydslRepository;

    @Test
    public void test() {
        dslDemoQuerydslRepository.findAll(QDslDemo.dslDemo.dslName.eq("lizhifu")).forEach(System.out::println);
    }
}
