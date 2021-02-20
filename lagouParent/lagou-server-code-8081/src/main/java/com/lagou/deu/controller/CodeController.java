package com.lagou.deu.controller;

import com.lagou.deu.entity.LagouAuthCode;
import com.lagou.deu.feignclient.EmailFeignClient;
import com.lagou.deu.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private EmailFeignClient emailFeignClient;

    //⽣成验证码并发送到对应邮箱，成功true，失败
    //false
    @RequestMapping("/create/{email}")
    public Boolean create(@PathVariable String email){
        StringBuffer buf = new StringBuffer(String.valueOf((int)(1+Math.random()*(9-1+1))));
        buf.append(String.valueOf((int)(1+Math.random()*(9-1+1))));
        buf.append(String.valueOf((int)(1+Math.random()*(9-1+1))));
        buf.append(String.valueOf((int)(1+Math.random()*(9-1+1))));
        buf.append(String.valueOf((int)(1+Math.random()*(9-1+1))));
        buf.append(String.valueOf((int)(1+Math.random()*(9-1+1))));
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setCode(buf.toString());
        Date creat = new Date();
        Date expiret = new Date();
        //10分钟
        expiret.setTime(creat.getTime()+1000*60*10);
        lagouAuthCode.setCreatetime(creat);
        lagouAuthCode.setExpiretime(expiret);
        lagouAuthCode.setEmail(email);
        if(codeService.addCode(lagouAuthCode)){
            //调用email发送邮件
            return emailFeignClient.sandCode(email,lagouAuthCode.getCode());
        }
        return false;
    }

    //校验验证码是否正确，0正确1错误2超时
    @RequestMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable String email,@PathVariable String code){
        LagouAuthCode lagouAuthCode = codeService.checkCode(email, code);
        if(lagouAuthCode!=null){
            if(new Date().before(lagouAuthCode.getExpiretime())){
                return 0;
            }
            return 1;
        }
        return 1;
    }
}
