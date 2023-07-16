package com.boot.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ConditionalOnProperty 示例
 *
 * @author lizhifu
 * @since 2023/7/16
 */
@Data
@ConfigurationProperties(prefix = "conditional.demo")
public class ConditionalOnPropertyProperties {
    private String name;
    private Integer age;
}
