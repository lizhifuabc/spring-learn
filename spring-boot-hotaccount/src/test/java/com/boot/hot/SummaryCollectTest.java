package com.boot.hot;

import com.boot.hot.collect.SummaryCollect;
import com.boot.hot.dao.AccountHisDao;
import com.boot.hot.dao.vo.AccountHisVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * SummaryCollect
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@SpringBootTest
public class SummaryCollectTest {
    private Long accountNo = 100000L;
    @Resource
    SummaryCollect summaryCollect;
    @Resource
    AccountHisDao accountHisDao;
    @Test
    public void add(){
        List<AccountHisVO> idList = accountHisDao.selectAddIdList(accountNo);
        BigDecimal amount = accountHisDao.selectAddSum(accountNo);
        summaryCollect.addCollect(idList,amount,accountNo);
    }
    @Test
    public void reduce(){
        List<AccountHisVO> idList = accountHisDao.selectReduceIdList(accountNo);
        BigDecimal amount = accountHisDao.selectReduceSum(accountNo);
        summaryCollect.reduceCollect(idList,amount,accountNo);
    }
}
