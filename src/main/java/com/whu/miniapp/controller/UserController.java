package com.whu.miniapp.controller;

import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.UserMapper;
import com.whu.miniapp.service.UserService;

import com.whu.miniapp.util.Response;
import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping(value = "/userRegister")
    public Response userRegister(String user_name,String user_passcode,String user_phone,@Nullable String user_gender,@Nullable Integer user_age,@Nullable String user_career,@Nullable String user_addr,@Nullable String introduction){
        int ret = userService.userRegister(user_name,user_passcode,user_phone,user_gender,user_age,user_career,user_addr,introduction);
        Response response;
        switch (ret){
            case 0:
                response = new Response(0,"成功",null);
                break;
            case -1:
                response = new Response(-1,"用户名已被使用",null);
                break;
            case -2:
                response = new Response(-2,"手机号已被注册",null);
                break;
            default:
                response = new Response(-3,"失败",null);
        }
        return response;
    }

    //用户登录
    @PostMapping(value = "/userLogin")
    public Response userLogin(String input,String user_passcode){
        int ret = userService.userLogin(input,user_passcode);
        Response response;
        if(ret >= 0){
            response = new Response(0,"成功",ret);
            return response;
        }
        else if(ret == -1){
            response = new Response(-1,"用户不存在",null);
            return response;
        }
        else if(ret == -2){
            response = new Response(-2,"用户名或密码错误",null);
            return response;
        }
        else {
            response = new Response(-3,"用户已被禁用",null);
            return response;
        }
    }
}
