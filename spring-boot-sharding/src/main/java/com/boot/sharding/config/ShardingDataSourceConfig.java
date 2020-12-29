package com.boot.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ShardingConfig：配置
 * 根据 user_id 取模分库, 且根据 order_id 取模分表的 2 库 2 表的配置
 * @author lizhifu
 * @date 2020/12/29
 */
@Configuration
public class ShardingDataSourceConfig {
    @Bean
    public DataSource dataSource() throws SQLException {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig());

        // 配置分库算法 user_id % 2 结果作为库后缀
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法 order_id % 2 结果作为表后缀
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        //属性配置
        Properties properties = new Properties();
        //是否在日志中打印 SQL
        properties.setProperty("sql.show", "true");
        // 创建 ShardingSphereDataSource
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap(), Collections.singleton(shardingRuleConfig),properties);
    }

    /**
     * 配置 t_order 表规则
     * @return
     */
    private ShardingTableRuleConfiguration orderTableRuleConfig(){
        // 配置t_order表规则,逻辑表（LogicTable）:t_order
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration
                ("t_order", "ds${0..1}.t_order_${0..1}");
        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
        return orderTableRuleConfig;
    }

    /**
     * 配置数据源
     * @return
     */
    private Map<String, DataSource> dataSourceMap(){
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/my_db_0");
        dataSource1.setUsername("yfyf_dev");
        dataSource1.setPassword("T%F4eoBPQu3Ne");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/my_db_1");
        dataSource2.setUsername("yfyf_dev");
        dataSource2.setPassword("T%F4eoBPQu3Ne");
        dataSourceMap.put("ds1", dataSource2);

        return dataSourceMap;
    }
}
