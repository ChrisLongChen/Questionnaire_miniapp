package com.whu.miniapp.controller;

import com.whu.miniapp.entity.Suggest;
import com.whu.miniapp.service.SuggestService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author: 胡龙晨
 * Date: 2021-03-01
 */

@RestController
@RequestMapping(value = "/suggest",method = RequestMethod.GET)
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    //查看与联系开发方
    @PostMapping(value = "/contactDesigner")
    public HashMap<String, Object> contactDesigner(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        String content = obj.getString("content");
        int ret = suggestService.contactDesigner(userId,content);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "发送成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }
}
