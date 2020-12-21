package com.boot.hot;

import com.boot.hot.dao.AccountHisDao;
import com.boot.hot.dao.param.AccountHisParam;
import com.boot.hot.enums.DirectionType;
import com.boot.hot.enums.TradeType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * AccountHisDao
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@SpringBootTest
public class AccountHisDaoTest {
    private Long accountNo = 100000L;
    @Resource
    AccountHisDao accountHisDao;
    @Test
    public void insert(){
        for (int i = 0; i < 1; i++) {
            Random random = new Random();
            AccountHisParam accountHisParam = new AccountHisParam();
            accountHisParam.setAccountNo(accountNo);
            accountHisParam.setAmount(new BigDecimal(random.nextDouble()));
            accountHisParam.setTradeFlowNo(UUID.randomUUID().toString());
            accountHisParam.setTradeSignNo(100000L);
            accountHisParam.setTradeType(TradeType.ORDER);
            accountHisParam.setDirection(DirectionType.ADD);
            System.out.println(accountHisDao.insert(accountHisParam));
        }
    }
}
