package com.lagou.deu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
@EnableFeignClients // 开启Feign
public class CodeApplication8081 {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication8081.class,args);
    }
}
