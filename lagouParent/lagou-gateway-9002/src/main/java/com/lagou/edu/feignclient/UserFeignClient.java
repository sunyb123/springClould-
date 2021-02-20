package com.lagou.edu.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("lagou-service-user")
public interface UserFeignClient {

    @RequestMapping("/user/info/{token}")
    public String info(@PathVariable String token) ;
}
