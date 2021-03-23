package com.whu.miniapp.controller;

import com.auth0.jwt.JWT;
import com.whu.miniapp.entity.User;
import com.whu.miniapp.service.UserService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * Author: 胡龙晨
 * Date: 2021-03-01
 */

@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserService userService;

    //获取用户信息
    @PostMapping(value = "/getUserInfo")
    public HashMap<String, Object> getUserInfo(HttpServletRequest httpServletRequest){
        HashMap<String, Object> res = new HashMap<>();
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println("获取用户信息请求头中的token:"+token);
        String user_id = JWT.decode(token).getKeyId();
        int userId = Integer.parseInt(user_id);
        System.out.println("获取用户信息userId:"+userId);
        User user = userService.getUserInfo(userId);
        if(user != null){
            res.put("code", 0);
            res.put("message", "成功");
            res.put("resData", user);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有用户信息");
        }
        return res;
    }

    //修改个人信息
    @PostMapping(value = "/modifyUserInfo")
    public HashMap<String, Object> modifyUserInfo(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        String userName = obj.getString("user_name");
        String userGender = obj.getString("user_gender");
        Integer userAge = obj.getInt("user_age");
        String userCareer = obj.getString("user_career");
        String userAddr = obj.getString("user_addr");
        String introduction = obj.getString("introduction");
        int ret = userService.modifyUserInfo(userId,userName,userGender,userAge,userCareer,userAddr,introduction);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "修改成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }


}
