package com.lagou.deu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurkeaServerApplicationA_8090 {
    public static void main(String[] args) {
        SpringApplication.run(EurkeaServerApplicationA_8090.class,args);
    }
}
