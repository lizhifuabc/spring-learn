package com.boot.payment.init;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Provider;
import java.security.Security;

/**
 * InitProvider
 *
 * @author lizhifu
 * @date 2021/2/22
 */
@Component
@Slf4j
public class InitProvider implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        Provider bouncyCastleProvider = new BouncyCastleProvider();
        Security.addProvider(bouncyCastleProvider);
    }
}
