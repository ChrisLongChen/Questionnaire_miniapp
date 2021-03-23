package com.whu.miniapp.controller;

import com.whu.miniapp.entity.User;
import com.whu.miniapp.service.WxService;

import com.whu.miniapp.util.JwtUtil;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class WxController {
    @Autowired
    protected JwtUtil jwtUtil;

    @Autowired
    private WxService wxService;

    //用户注册
    @PostMapping(value = "/userRegister")
    public HashMap<String, Object> userRegister(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        String user_name = obj.getString("user_name");
        String user_passcode = obj.getString("user_passcode");
        String user_gender = obj.getString("user_gender");
        Integer user_age = obj.getInt("user_age");
        String user_career = obj.getString("user_career");
        String user_phone = obj.getString("user_phone");
        String user_addr = obj.getString("user_addr");
        String introduction = obj.getString("introduction");
        int ret = wxService.userRegister(user_name,user_passcode,user_gender,user_age,user_career,user_phone,user_addr,introduction);
        switch (ret){
            case 0:
                res.put("code", 0);
                res.put("message", "成功");
                break;
            case -1:
                res.put("code", -1);
                res.put("message", "用户名已被使用");
                break;
            case -2:
                res.put("code", -2);
                res.put("message", "手机号已被注册");
                break;
            default:
                res.put("code", -3);
                res.put("message", "失败");
        }
        return res;
    }

    //用户登录
    @PostMapping(value = "/userLogin")
    public HashMap<String, Object> userLogin(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        String code = obj.getString("code");
        String input = obj.getString("input");
        String user_passcode = obj.getString("user_passcode");
        System.out.println("code:"+code);
        String openid = wxService.getOpenId(code);
        System.out.println("openid:"+openid);
        int ret = wxService.userLogin(openid,input,user_passcode);
        switch (ret){
            case 0:
                User user1 = wxService.returnInfoFromWechatid(openid);
                String token1 = jwtUtil.getToken(user1);
                System.out.println("token:"+token1);
                res.put("code", 0);
                res.put("message", "成功");
                res.put("resData", token1);
                break;
            case 1:
                User user2 = wxService.returnInfoFromUserPhone(input);
                String token2 = jwtUtil.getToken(user2);
                res.put("code", 0);
                res.put("message", "获取成功");
                res.put("resData", token2);
                break;
            case 2:
                User user3 = wxService.returnInfoFromUsername(input);
                String token3 = jwtUtil.getToken(user3);
                res.put("code", 0);
                res.put("message", "成功");
                res.put("resData", token3);
                break;
            case -1:
                res.put("code", -1);
                res.put("message", "用户不存在");
                break;
            case -2:
                res.put("code", -2);
                res.put("message", "用户名或密码错误");
                break;
            case -3:
                res.put("code", -3);
                res.put("message", "用户已被禁用");
                break;
            default:
                res.put("code", -4);
                res.put("message", "失败");
        }
        return res;
    }

    //用户自动登录
    @PostMapping(value = "/userAutoLogin")
    public HashMap<String, Object> userAutoLogin(@RequestHeader("Authorization") String token){
        HashMap<String, Object> res = new HashMap<>();
        int ret = wxService.userAutoLogin(token);
        switch (ret){
            case 0:
                res.put("code", 0);
                res.put("message", "成功");
                break;
            case -1:
                res.put("code", -1);
                res.put("message", "token为空");
                break;
            case -2:
                res.put("code", -2);
                res.put("message", "成功");
                break;
            default:
                res.put("code", -3);
                res.put("message", "成功");
        }
        return res;
    }

}
