package com.mybatis.flex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lizhifu
 */
@SpringBootApplication
@MapperScan("com.mybatis.flex.mapper")
public class MybatisFlexApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisFlexApplication.class, args);
	}

}
