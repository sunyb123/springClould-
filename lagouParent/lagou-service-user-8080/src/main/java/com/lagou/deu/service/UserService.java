package com.lagou.deu.service;

import com.lagou.deu.dao.LagouTokenDao;
import com.lagou.deu.dao.LagouUserDao;
import com.lagou.deu.entity.LagouToken;
import com.lagou.deu.entity.LagouUser;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private LagouTokenDao lagouTokenDao;

    @Autowired
    private LagouUserDao lagouUserDao;

    //添加用户
    public Boolean addUser(LagouUser lagouUser){
        try {
            lagouUserDao.saveAndFlush(lagouUser);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //保存token
    public Boolean addToken(LagouToken lagouToken){
        try {
            if(StringHelper.isNotEmpty(lagouToken.getEmail())&&StringHelper.isNotEmpty(lagouToken.getToken())){
                lagouTokenDao.saveAndFlush(lagouToken);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //通过token查询
    public LagouToken findByToken(String token){
        LagouToken byEmail = lagouTokenDao.findByToken(token);
        return byEmail;
    }

    //查看是否已经注册
    public Boolean existByName(String name){
        List<LagouUser> lagouUsers = lagouUserDao.findByName(name);
        if(lagouUsers!=null&&lagouUsers.size()>0){
            return true;
        }
        return false;
    }

    //通过用户名密码查找
    public LagouUser login(String name,String password){
        List<LagouUser> lagouUsers = lagouUserDao.findByNameAndPassword(name,password);
        if(lagouUsers!=null){
            return lagouUsers.get(0);
        }
        return null;
    }

}
