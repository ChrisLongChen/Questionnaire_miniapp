package com.whu.miniapp.controller;

import com.whu.miniapp.entity.Administrator;
import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.AdministratorMapper;
import com.whu.miniapp.mapper.UserMapper;
import com.whu.miniapp.service.AdministratorService;

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

@RestController
@RequestMapping(value = "/admin")
public class AdministratorController {
    @Autowired(required = false)
    private AdministratorMapper administratorMapper;

    @Autowired
    private AdministratorService administratorService;

    //管理员登录
    @PostMapping(value = "/userLogin")
    public Response userLogin(String input,String admin_password){
        int ret = administratorService.adminLogin(input,admin_password);
        Response response;
        if(ret >= 0){
            response = new Response(0,"成功",ret);
            return response;
        }
        else if(ret == -1){
            response = new Response(-1,"管理员不存在",null);
            return response;
        }
        else {
            response = new Response(-2,"用户名或密码错误",null);
            return response;
        }
    }
}
