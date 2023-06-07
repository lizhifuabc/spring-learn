package com.spring.cloud.openfeign.one.controller;

import com.spring.cloud.openfeign.one.feign.TwoFeign;
import com.spring.cloud.openfeign.one.feign.TwoFeignHttp;
import com.spring.cloud.openfeign.one.feign.TwoFeignLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * one controller
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@RestController
@RequestMapping("/one")
public class OneController {
    private final TwoFeign twoFeign;
    private final TwoFeignLog twoFeignLog;
    public OneController(TwoFeign twoFeign, TwoFeignLog twoFeignLog) {
        this.twoFeign = twoFeign;
        this.twoFeignLog = twoFeignLog;
    }

    @GetMapping("/one")
    public String one(){
        return twoFeign.two();
    }

    @GetMapping("/logFull")
    public String logFull(){
        return twoFeignLog.two();
    }

    @GetMapping("/http")
    public String http(){
        //构建一个web客户端
        WebClient webClient = WebClient.builder().build();
        //根据web客户端去构建服http服务的代理工厂
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        //根据代理工厂创建代理类
        TwoFeignHttp twoFeignHttp = factory.createClient(TwoFeignHttp.class);
        return twoFeignHttp.two();
    }
}
