package com.spring.boot.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security 白名单资源路径配置
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    /**
     * 白名单
     */
    private List<String> urls = new ArrayList<>();

    public AntPathRequestMatcher[] getAntPathRequestMatcher() {
        // 白名单路径，基于Ant风格模式进行匹配的请求路径
        return this.getUrls()
                .stream()
                .map(AntPathRequestMatcher::new)
                .toList()
                .toArray(new AntPathRequestMatcher[] {});
    }
}
