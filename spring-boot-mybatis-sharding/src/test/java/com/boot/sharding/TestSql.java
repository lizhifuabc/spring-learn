package com.boot.sharding;

import com.boot.sharding.util.StrUtil;
import org.junit.jupiter.api.Test;


/**
 * TestSql
 *
 * @author lizhifu
 * @date 2020/12/30
 */
public class TestSql {
    @Test
    public void test(){
        String sql = "select * from t_order where user_id = ? and order_id = ?";
        System.out.println(StrUtil.sumNumber(sql,"\\?"));
        System.out.println(sql);
        System.out.println(sql.split("order_id").length);

        String str = "order_id";
        String[] array = sql.split(str);
        int pre = StrUtil.sumNumber(array[0],"\\?");
        int next = StrUtil.sumNumber(array[1],"\\?");
        System.out.println(pre+":"+next);
    }
}
