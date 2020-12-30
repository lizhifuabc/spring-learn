package com.boot.sharding;

import com.boot.sharding.strategy.TableShardingStrategy;
import com.boot.sharding.strategy.entity.LongTableShardingEntity;
import com.boot.sharding.strategy.impl.LongHashTableShardingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * TableShardingStrategyTest
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class TableShardingStrategyTest {
    @Test
    public void testLongHashTableShardingStrategy(){
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TableShardingStrategy tableShardingStrategy = new LongHashTableShardingStrategy();
            LongTableShardingEntity param = new LongTableShardingEntity();
            Long val =  random.nextLong();
            param.setShardingKey(val);
            String tableName = tableShardingStrategy.getTableName("t_order",param);
            if(val > 0){
                System.out.println(val+":"+tableName);
            }
        }
    }
}
