package com.cpt202.group7.controller;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.service.CustomerLogin;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Slf4j
public class mainController {
    //将Service注入Web层
    @Resource
    CustomerLogin customerLogin;

    //实现登录
    @RequestMapping("/login")
    public String show(){
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username,String password){
        User userBean = customerLogin.LoginIn(username, password);
        log.info("name:{}",username);
        log.info("password:{}",password);
        if(userBean!=null){
            return "redirect:/index";
        }else {
            return "error";
        }
    }
    @RequestMapping("/index")
    public String mainPage(){
        return "index";
    }
}
