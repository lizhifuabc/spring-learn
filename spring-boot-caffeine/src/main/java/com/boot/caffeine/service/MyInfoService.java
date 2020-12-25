package com.boot.caffeine.service;

import com.boot.caffeine.domain.MyInfo;

/**
 * MyInfoService
 *
 * @author lizhifu
 * @date 2020/12/25
 */
public interface MyInfoService {

    public MyInfo insert(MyInfo myInfo);

    MyInfo selectById(Integer id);
}
