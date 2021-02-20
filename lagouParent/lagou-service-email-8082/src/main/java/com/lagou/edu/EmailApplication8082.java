package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailApplication8082 {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication8082.class,args);
    }
}
