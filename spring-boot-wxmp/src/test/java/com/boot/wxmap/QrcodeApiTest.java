package com.boot.wxmap;

import com.boot.wxmap.api.QrcodeApi;
import com.boot.wxmap.api.qrcode.EQRActionName;
import com.boot.wxmap.api.qrcode.QRCodeParam;
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
        QrcodeApi qrcodeApi = new QrcodeApi();
        QRCodeParam codeParam = QRCodeParam.builder()
                .accessToken("41_vLe1lEHJCj9tAxJmrvEhm5eM-XRXf0lxvujntue2GsTEmx0nOQhCT2Tsm8LnlLguQNrx0sNAeSU7tSoKQ5oArMpAI25ivf3sQao-gCRWqRGPCS0ICsmL1r6_5Lf0TH-WYqIyhmZaE38t6YrPDWLdAJAMFU")
                .action_name(EQRActionName.QR_STR_SCENE)
                .expire_seconds((3*60*60l))
                .build();
        qrcodeApi.create(codeParam);
    }
}
