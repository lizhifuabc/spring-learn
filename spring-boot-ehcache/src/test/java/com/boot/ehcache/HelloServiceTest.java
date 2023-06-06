package com.boot.ehcache;

import com.boot.ehcache.domain.Hello;
import com.boot.ehcache.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * HelloService 测试
 *
 * @author lizhifu
 * @date 2020/12/9
 */
@SpringBootTest
public class HelloServiceTest {
    @Resource
    HelloService helloService;
    @Test
    public void test(){
        Hello hello = new Hello();
        hello.setId(1L);
        hello.setFrom("l");
        hello.setTo("m");
        //加入缓存
        helloService.insert(hello);
        //查询缓存
        helloService.select(1L);
        //删除缓存
        helloService.delete(1L);
        //查询缓存
        helloService.select(1L);
    }
}
