package com.balance.client.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * LoadBalancer的后端服务实例
 *
 * @author lizhifu
 * @date 2021/1/15
 */
@Configuration
public class SayHelloConfiguration {
    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("say-hello");
    }
    class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {

        private final String serviceId;

        DemoServiceInstanceListSuppler(String serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        public String getServiceId() {
            return serviceId;
        }

        @Override
        public Flux<List<ServiceInstance>> get() {
            //负载均衡的后端实例的地址
            return Flux.just(Arrays
                    .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8090, false)
                    ));
        }
    }
}
