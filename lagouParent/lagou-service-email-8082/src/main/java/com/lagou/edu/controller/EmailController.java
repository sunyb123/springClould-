package com.lagou.edu.controller;

import com.lagou.edu.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/email/{email}/{code}")
    public Boolean email(@PathVariable String email,@PathVariable String code){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailConfig.getEmailFrom());
            message.setTo(email);
            message.setSubject("拉钩验证码");
            message.setText(code);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
