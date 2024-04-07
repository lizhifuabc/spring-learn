package com.spring.boot.security.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * JWT 工具类
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@Slf4j
public class JwtTokenUtil {

    /**
     * JWT 过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    /**
     * 根据负载生成 JWT 的 token
     * @param roles 角色
     * @return JWT 的 token
     */
    public String generateToken(String username, Collection<?> roles){
        Map<String, Object> claims = Map.of("roles", roles);
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
    /**
     * 从 token 中获取 JWT 中的负载
     * @param token JWT 的 token
     * @return JWT 中的负载
     */
    public Claims getClaims(String token){
        if (Objects.isNull(token)){
            return null;
        }
        try {
            Jwe<Claims> claimsJwe = Jwts.parser()
                    .decryptWith(privateKey)
                    .build()
                    .parseEncryptedClaims(token);
            return claimsJwe.getPayload();
        }catch (JwtException e){
            log.error("JWT 校验失败: {}", e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //获取byte数组
        byte[] publicKeyEncode = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        //进行Base64编码
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKeyEncode);
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKeyEncoded);
        System.out.println("publicKeyStr: " + publicKeyStr);
        System.out.println("privateKeyStr: " + privateKeyStr);
    }
}
