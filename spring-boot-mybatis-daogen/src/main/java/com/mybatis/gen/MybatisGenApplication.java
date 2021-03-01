package com.mybatis.gen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lizhifu
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mybatis.gen.mapper"})
public class MybatisGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisGenApplication.class, args);
	}

}
