package com.boot.payment;

import com.boot.payment.enumeration.WechatPayV3Type;
import com.boot.payment.enumeration.WxServer;
import com.boot.payment.sign.PemUtil;
import com.boot.payment.sign.WxSignProvide;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.UUID;

/**
 * PemUtil
 *
 * @author lizhifu
 * @date 2021/1/19
 */
public class PemUtilTest {
    private static final RestOperations restOperations = new RestTemplate();

    public static void main(String[] args) throws IOException {
        //EnblF13ofwZdyOhDrE7NXGUqp0hz2Hyl
        long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String nonceStr = UUID.randomUUID()
                .toString()
                .replaceAll("-", "");

        FileInputStream resource = new FileInputStream("/Users/lizhifu/cert/1605765479_20210119_cert/apiclient_key.pem");
        PrivateKey privateKey = PemUtil.loadPrivateKey(resource);
        System.out.println(privateKey.getFormat());
        FileInputStream resource2 = new FileInputStream("/Users/lizhifu/cert/1605765479_20210119_cert/apiclient_cert.pem");
        X509Certificate x509Certificate = PemUtil.loadCertificate(resource2);
        System.out.println(x509Certificate.getSerialNumber());

        String url = WechatPayV3Type.CERT.uri(WxServer.CHINA);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        String canonicalUrl = uri.getPath();
        String encodedQuery = uri.getQuery();

        if (encodedQuery != null) {
            canonicalUrl += "?" + encodedQuery;
        }
        // 签名
        HttpMethod httpMethod = WechatPayV3Type.CERT.method();
        String authorization = WxSignProvide.authorization(httpMethod.name(),canonicalUrl,"","5BAB830441024763C885D9DE9C4CDE515F5083CE","1605765479",privateKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization);
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, httpMethod, uri.toUri());
        ResponseEntity<ObjectNode> responseEntity = restOperations.exchange(requestEntity, ObjectNode.class);
        ObjectNode bodyObjectNode = responseEntity.getBody();

        System.out.println(bodyObjectNode.toString());

    }
}
