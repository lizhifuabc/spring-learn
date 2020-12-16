package com.boot.pk.snowflake;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * snowflake算法
 * 1 bit：统一都是 0,正数
 * 41 bits：表示的是时间戳，单位是毫秒
 * 10 bits：记录工作机器 id，5 个 bits 代表机房 id，5 个 bits 代表机器 id
 * 12 bits：这个是用来记录同一个毫秒内产生的不同 id
 * @author lizhifu
 * @date 2020/12/16
 */
@RestController
public class SnowflakeController {
    @GetMapping("/snowflake")
    public long snowflake(){
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
        return snowflakeIdWorker.nextId();
    }
}
