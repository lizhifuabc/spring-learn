package com.spring.boot.actuator.custom;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 定制健康状态对象（Health） 返回
 *
 * @author lizhifu
 * @since 2023/6/11
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    private final CustomMeter customMeter;

    public CustomHealthIndicator(CustomMeter customMeter) {
        this.customMeter = customMeter;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // 自定义检查方法
        int check = customMeter.check();
        if(check == 1){
            //存活
            builder.up()
                    .withDetail("code","1000")
                    .withDetail("msg","存活")
                    .withDetail("data","存活")
                    .build();
        }else {
            //下线
            builder.down()
                    .withDetail("code","1001")
                    .withDetail("msg","下线")
                    .withDetail("data","下线")
                    .build();
        }
    }
}
