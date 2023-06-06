package com.boot.hot.collect;

import com.boot.hot.dao.AccountDao;
import com.boot.hot.dao.AccountHisDao;
import com.boot.hot.dao.vo.AccountHisVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 汇总入账
 *
 * @author lizhifu
 * @date 2020/12/18
 */
@Service
public class SummaryCollect {
    @Resource
    private AccountHisDao accountHisDao;
    @Resource
    private AccountDao accountDao;

    /**
     * 增款
     * @param idList
     * @param amount
     * @param accountNo
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addCollect(List<AccountHisVO> idList, BigDecimal amount, Long accountNo){
        accountHisDao.batchUpdate(idList);
        int count = accountDao.add(amount,accountNo);
        if(count == 0){
            throw new RuntimeException("增款失败");
        }
    }

    /**
     * 减款
     * @param idList
     * @param amount
     * @param accountNo
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void reduceCollect(List<AccountHisVO> idList, BigDecimal amount,Long accountNo){
        accountHisDao.batchUpdate(idList);
        int count = accountDao.reduce(amount,accountNo);
        if(count == 0){
            throw new RuntimeException("减款失败");
        }
    }
}
