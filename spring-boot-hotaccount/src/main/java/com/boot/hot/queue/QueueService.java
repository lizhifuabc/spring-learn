package com.boot.hot.queue;

import com.boot.hot.dao.AccountHisDao;
import com.boot.hot.dao.param.AccountHisParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 队列
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@Service
public class QueueService {
    @Resource
    private AccountHisDao accountHisDao;
    public static final ConcurrentHashMap<Long, AccountRequest> map = new ConcurrentHashMap();

    public void add(AccountHisParam accountHisParam){
        //记录账户历史
        Long id = accountHisDao.insert(accountHisParam);
        AccountRequest param = new AccountRequest();
        param.setAccountNo(accountHisParam.getAccountNo());
        param.setQueue(new LinkedBlockingQueue());
        //如果不存在，就添加key和value，返回null
        AccountRequest accountRequest = map.putIfAbsent(accountHisParam.getAccountNo(),param);
        //返回存在的value
        if(null != accountRequest){
            accountRequest.add(id);
        }
    }
}
