package com.lagou.deu.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "lagou-server-email")
public interface EmailFeignClient {
    @RequestMapping(value = "/email/{email}/{code}",method = RequestMethod.GET)
    public Boolean sandCode(@PathVariable(value = "email") String email,@PathVariable(value = "code") String code);
}
