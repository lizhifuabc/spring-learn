package com.boot.sharding.config;

import org.springframework.context.annotation.Configuration;

/**
 * ShardingConfig：配置
 * 根据 user_id 取模分库, 且根据 order_id 取模分表的 2 库 2 表的配置
 * @author lizhifu
 * @since  2020/12/29
 */
@Configuration
public class ShardingDataSourceConfig {
}
