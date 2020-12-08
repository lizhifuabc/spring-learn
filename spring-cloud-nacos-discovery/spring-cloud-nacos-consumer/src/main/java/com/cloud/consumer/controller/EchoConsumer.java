package com.cloud.consumer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * echo消费端
 *
 * @author lizhifu
 * @date 2020/12/1
 */
@RestController
public class EchoConsumer {
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        System.out.println("EchoConsumer 开始访问");
        return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
    }
}
