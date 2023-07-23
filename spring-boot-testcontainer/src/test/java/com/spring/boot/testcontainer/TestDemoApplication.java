package com.spring.boot.testcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestDemoApplication {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>("mysql:latest")
				.withInitScript("db/db.sql")
				;
	}

	public static void main(String[] args) {
		SpringApplication.from(DemoApplication::main).with(TestDemoApplication.class).run(args);
	}

}
