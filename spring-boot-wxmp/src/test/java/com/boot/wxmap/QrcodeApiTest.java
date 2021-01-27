package com.boot.wxmap;

import com.boot.wxmap.api.QrcodeApi;
import com.boot.wxmap.api.qrcode.ActionInfo;
import com.boot.wxmap.api.qrcode.EQRActionName;
import com.boot.wxmap.api.qrcode.QRCodeParam;
import com.boot.wxmap.api.qrcode.Scene;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * QrcodeApiTest
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@SpringBootTest
public class QrcodeApiTest {
    @Test
    public void create(){
        String senceNo = "QrcodeApiTest";
        Scene scene = Scene.builder()
                .scene_str(senceNo)
                .build();
        ActionInfo actionInfo = ActionInfo.builder()
                .scene(scene)
                .build();
        QrcodeApi qrcodeApi = new QrcodeApi();
        QRCodeParam codeParam = QRCodeParam.builder()
                .accessToken("41_6jzZ_6MKXAUshXhholxAFyYCLAym4eFZ6K_vRb-VxH_eXscylSN_rOWYSdTA9LbhdZUfM1VyI2NAuecjk503ZU1pGFeMpyR56AZtUvM6-pnjt5ISFxSqsBMgqF6G7LVqjkUjwAxxh9-SFlqVVLKbAAABRQ")
                .action_name(EQRActionName.QR_STR_SCENE)
                .action_info(actionInfo)
                .expire_seconds((3*60*60l))
                .build();
        qrcodeApi.create(codeParam);
    }
}
