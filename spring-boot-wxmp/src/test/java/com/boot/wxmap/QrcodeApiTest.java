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
                .accessToken("41_Ry3W34xZpHOPFFjcsio543fVEfa4ZKkR3XLoGa_A0OtL5a7odDZL_u1rw3yHf_8ntQWGI3I3itbxEOhHcVSavJ7YXQBTpHQdvmKzTyXRlIHgVC_LcfEcgB2G6KTv9kOWyrR5h7tpzQpO_8anKLKcACAXYX")
                .action_name(EQRActionName.QR_STR_SCENE)
                .action_info(actionInfo)
                .expire_seconds((12*60*60l))
                .build();
        qrcodeApi.create(codeParam);
    }
}
