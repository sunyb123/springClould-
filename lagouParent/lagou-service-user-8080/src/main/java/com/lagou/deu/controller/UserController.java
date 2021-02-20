package com.lagou.deu.controller;

import com.lagou.deu.entity.LagouToken;
import com.lagou.deu.entity.LagouUser;
import com.lagou.deu.feignclient.CodeFeignClient;
import com.lagou.deu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeFeignClient codeFeignClient;

    //注册接⼝，true成功，false失败
    @RequestMapping("/register/{email}/{password}/{code}")
    public Integer register(@PathVariable String email,@PathVariable String password,@PathVariable String code){
        //用code验证验证码是否正确
        try{
            Integer flag = codeFeignClient.validate(email, code);
            if(0==flag){
                //正确
                LagouUser lagouUser = new LagouUser();
                lagouUser.setName(email);
                lagouUser.setPassword(password);
                userService.addUser(lagouUser);
            }
            return flag;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    //是否已注册，根据邮箱判断,true代表已经注册过，
    //false代表尚未注册
    @RequestMapping("/isRegistered/{email}")
    public Boolean isRegistered(@PathVariable String email){
        Boolean flag = userService.existByName(email);
        return flag;
    }

    //登录接⼝，验证⽤户名密码合法性，根据⽤户名和
    //密码⽣成token，token存⼊数据库，并写⼊cookie
    //中，登录成功返回邮箱地址，重定向到欢迎⻚
    @RequestMapping("/login/{email}/{password}")
    public String login(HttpServletResponse response, @PathVariable String email, @PathVariable String password){

        LagouUser user = userService.login(email, password);
        if(user!=null){
            String token = UUID.randomUUID().toString();
            LagouToken lagouToken = new LagouToken();
            lagouToken.setEmail(email);
            lagouToken.setToken(token);
            if(userService.addToken(lagouToken)){
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return email;
            }
        }
        return "";
    }

    //根据token查询⽤户登录邮箱接⼝
    @RequestMapping("/info/{token}")
    public String info(@PathVariable String token){
        LagouToken lagouToken = userService.findByToken(token);
        if(lagouToken!=null){
            return lagouToken.getEmail();
        }
        return "";
    }
}
