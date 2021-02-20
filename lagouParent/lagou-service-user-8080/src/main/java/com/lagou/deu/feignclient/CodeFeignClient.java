package com.lagou.deu.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("lagou-service-code")
public interface CodeFeignClient {

    @RequestMapping(value = "/code/validate/{email}/{code}",method = RequestMethod.GET)
    public Integer validate(@PathVariable("email") String email, @PathVariable("code") String code);
}
