package com.boot.p6spy;

import com.boot.p6spy.repository.CityRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 城市
 *
 * @author lizhifu
 * @since 2023/7/17
 */
@SpringBootTest
public class CityRepositoryTest {
    @Resource
    private CityRepository cityRepository;

    @Test
    public void test(){
        System.out.println(cityRepository.findById(1L));
    }
}
