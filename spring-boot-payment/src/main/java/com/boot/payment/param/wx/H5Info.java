package com.boot.payment.param.wx;

import lombok.Data;

/**
 * H5场景信息
 * @author lizhifu
 */
@Data
public class H5Info {
    /**
     * 场景类型
     * 示例值：iOS, Android, Wap
     */
    private String type;
    /**
     * 应用名称
     * 示例值：王者荣耀
     */
    private String appName;
    /**
     * 网站URL
     * 示例值：https://pay.qq.com
     */
    private String appUrl;
    /**
     * iOS平台BundleID
     * 示例值：com.tencent.wzryiOS
     */
    private String bundleId;
    /**
     * Android平台PackageName
     * 示例值：com.tencent.tmgp.sgame
     */
    private String packageName;
}
