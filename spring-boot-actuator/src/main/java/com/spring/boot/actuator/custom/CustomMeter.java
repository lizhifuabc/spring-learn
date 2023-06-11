package com.spring.boot.actuator.custom;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * CustomMeter
 *
 * @author lizhifu
 * @since 2023/6/11
 */
@Component
public class CustomMeter {
    private final Counter counter;
    private final MeterRegistry meterRegistry;

    public CustomMeter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        // custommeter.hello 计数器
        counter = meterRegistry.counter("custommeter.hello");
    }

    public  int check(){
        // TODO 业务代码判断存活状态
        return 1;
    }

    public void hello(){
        System.out.println("hello");
        counter.increment();
    }
}
