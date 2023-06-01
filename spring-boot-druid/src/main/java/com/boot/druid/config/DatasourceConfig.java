package com.boot.druid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author lizhifu
 * @since 2023/6/1
 */
@Configuration
public class DatasourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource one() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource two() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate oneJdbcTemplate(@Qualifier("one") DataSource one) {
        return new JdbcTemplate(one);
    }

    @Bean
    public JdbcTemplate twoJdbcTemplate(@Qualifier("two") DataSource two) {
        return new JdbcTemplate(two);
    }
}
