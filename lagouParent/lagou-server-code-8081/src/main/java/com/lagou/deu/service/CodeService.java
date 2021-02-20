package com.lagou.deu.service;

import com.lagou.deu.dao.LagouAuthCodeDao;
import com.lagou.deu.entity.LagouAuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CodeService {
    @Autowired
    private LagouAuthCodeDao lagouAuthCodeDao;

    //记录验证码
    public Boolean addCode(LagouAuthCode code){
        try {
            lagouAuthCodeDao.saveAndFlush(code);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //验证验证码是否正确
    public LagouAuthCode checkCode(String email,String code ){
        List<LagouAuthCode> lagouAuthCodes = lagouAuthCodeDao.findByEmailAndCode(email,code);
        if(lagouAuthCodes!=null){
            return lagouAuthCodes.get(0);
        }
        return null;
    }
}
