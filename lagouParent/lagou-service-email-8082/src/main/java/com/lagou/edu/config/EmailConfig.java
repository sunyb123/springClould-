package com.lagou.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class EmailConfig {

    /**
     * 发件邮箱
     */

    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

//    public void setEmailFrom(String emailFrom) {
//        this.emailFrom = emailFrom;
//
//    }
}
