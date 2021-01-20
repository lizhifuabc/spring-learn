package com.boot.payment.init;

import com.boot.payment.properties.WxProperties;
import com.boot.payment.sign.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * 数据初始化
 *
 * @author lizhifu
 * @date 2021/1/19
 */
@Component
@Slf4j
public class WxMetaDataInit implements InitializingBean {
    @Resource
    private WxProperties wxProperties;
    @Override
    public void afterPropertiesSet(){
        Map<String, WxProperties.Detail> data = wxProperties.getData();
        data.forEach((k,v)->{
            log.info("WxMetaDataInit k {},v{}",k,v);
            WxMetaData wxMetaData = new WxMetaData();
            wxMetaData.setDetail(v);
            try {
                FileInputStream resource = new FileInputStream(v.getCertPath());
                wxMetaData.setPrivateKey(PemUtil.loadPrivateKey(resource));
            } catch (FileNotFoundException e) {
                log.error("WxMetaDataInit FileNotFoundException ",e);
            }
            WxMetaDataConstant.INSTANCE.setWxMetaData(k,wxMetaData);
        });
    }
}
