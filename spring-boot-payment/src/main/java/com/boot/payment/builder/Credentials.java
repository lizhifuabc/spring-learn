package com.boot.payment.builder;

import org.apache.http.client.methods.HttpRequestWrapper;

import java.io.IOException;

/**
 * 证书
 */
public interface Credentials {

  String getSchema();

  String getToken(HttpRequestWrapper request) throws IOException;
}
