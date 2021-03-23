package com.whu.miniapp.controller;

import com.whu.miniapp.service.ComplaintService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-03-12
 */

@RestController
@RequestMapping(value = "/complaint",method = RequestMethod.GET)
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    //用户举报问卷
    @PostMapping(value = "/reportQues")
    public HashMap<String, Object> reportQues(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        Integer questionnaireId = obj.getInt("questionnaireId");
        String reason = obj.getString("reason");
        int ret = complaintService.reportQues(userId,questionnaireId,reason);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "举报成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }
}
