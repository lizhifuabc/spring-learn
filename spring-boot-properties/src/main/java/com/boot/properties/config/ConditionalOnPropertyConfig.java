package com.boot.properties.config;

import com.boot.properties.properties.ConditionalOnPropertyProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ConditionalOnProperty 示例
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(ConditionalOnPropertyProperties.class)
//@ConditionalOnProperty(prefix = "conditional.demo", name = "name", havingValue = "lizhifu")
@ConditionalOnExpression("'${conditional.demo.name}' == 'lizhifu' and '${conditional.demo.age}' == '18'")
public class ConditionalOnPropertyConfig {
    @PostConstruct
    public void init() {
        log.info("ConditionalOnPropertyConfig init");
    }
}
