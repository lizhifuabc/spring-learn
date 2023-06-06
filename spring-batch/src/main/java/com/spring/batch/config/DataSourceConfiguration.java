package com.spring.batch.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * 初始化 spring batch 数据库脚本
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Configuration
@PropertySource("classpath:/batch-mysql.properties")
@Slf4j
public class DataSourceConfiguration {
    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    protected void initialize() {
        log.info("初始化 spring batch 数据库脚本");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(resourceLoader.getResource(Objects.requireNonNull(environment.getProperty("batch.schema.script"))));
        populator.setContinueOnError(true);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
