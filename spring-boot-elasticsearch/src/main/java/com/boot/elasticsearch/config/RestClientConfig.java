package com.boot.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * RestClientConfig
 *
 * @author lizhifu
 * @date 2021/4/14
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        // 使用构建器来提供集群地址，设置默认值HttpHeaders或启用SSL。
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                // 可以设置多个地址
                // .connectedTo("localhost:9200", "localhost:9291")
                // 是否启用ssl
                // .usingSsl()
                // 设置超时时间
                .withConnectTimeout(10L)
                // 设置用户名密码
                // .withBasicAuth("elastic", "123456")
                // 创建连接信息
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
