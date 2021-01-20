package com.boot.payment.init;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WxMetaData数据
 *
 * @author lizhifu
 * @date 2021/1/19
 */
public enum WxMetaDataConstant {
    INSTANCE;
    /**
     * 管理动微信数据
     */
    public static final Map<String, WxMetaData> WX_METADATA = new ConcurrentHashMap<>();

    public void setWxMetaData(String appId, WxMetaData wxMetaData) {
        WX_METADATA.put(appId,wxMetaData);
    }
    public WxMetaData getWxMetaData(String appId) {
        return WX_METADATA.get(appId);
    }
}
