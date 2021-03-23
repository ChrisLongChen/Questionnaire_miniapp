package com.whu.miniapp.controller;

import com.whu.miniapp.entity.*;
import com.whu.miniapp.service.AdministratorService;

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
@RequestMapping(value = "/admin",method = RequestMethod.GET)
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    //修改管理员信息
    @PostMapping(value = "/modifyAdminInfo")
    public HashMap<String, Object> modifyAdminInfo(Integer adminId,String adminName,Integer adminAge,String adminGender,String adminCareer){
        HashMap<String, Object> res = new HashMap<>();
        int ret = administratorService.modifyAdminInfo(adminId,adminName,adminAge,adminGender,adminCareer);
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

    //管理员查看问卷列表
    @PostMapping(value = "/adminGetList")
    public HashMap<String, Object> adminGetList(){
        HashMap<String, Object> res = new HashMap<>();
        List<Questionnaire> questionnaires = administratorService.adminGetList();
        if(questionnaires != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaires);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员删除问卷
    @PostMapping(value = "/adminDeleteQuestionnaire")
    public HashMap<String, Object> adminDeleteQuestionnaire(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        int ret = administratorService.adminDeleteQuestionnaire(questionnaireId);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "删除成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员查看用户列表
    @PostMapping(value = "/checkUser")
    public HashMap<String, Object> checkUser(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        List<User> users = administratorService.checkUser();
        if(users != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", users);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员搜索用户
    @PostMapping(value = "/searchUser")
    public HashMap<String, Object> searchUser(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        String input = obj.getString("input");
        List<User> users = administratorService.searchUser(input);
        if(users != null){
            res.put("code", 0);
            res.put("message", "搜索成功");
            res.put("resData", users);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员查看用户信息
    @PostMapping(value = "/checkUserInfo")
    public HashMap<String, Object> checkUserInfo(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        User user = administratorService.checkUserInfo(userId);
        if(user != null){
            res.put("code", 0);
            res.put("message", "查看成功");
            res.put("resData", user);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员查看某用户发布的问卷
    @PostMapping(value = "/checkUserQuestionnaire")
    public HashMap<String, Object> checkUserQuestionnaire(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        List<Questionnaire> questionnaires = administratorService.checkUserQuestionnaire(userId);
        if(questionnaires != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaires);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //管理员封禁用户
    @PostMapping(value = "/banUser")
    public HashMap<String, Object> banUser(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        String banUnit = obj.getString("ban_unit");
        Integer banTime = obj.getInt("ban_time");
        int ret = administratorService.banUser(userId,banUnit,banTime);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "封禁成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //管理员查看答卷列表
    @PostMapping(value = "/checkAnswerList")
    public HashMap<String, Object> checkAnswerList(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        List<Answer> answers = administratorService.checkAnswerList();
        if(answers != null){
            res.put("code", 0);
            res.put("message", "封");
            res.put("resData", answers);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //管理员查看答卷详情
    @PostMapping(value = "/checkAnswerInfo")
    public HashMap<String, Object> checkAnswerInfo(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer answerSheetId = obj.getInt("answer_sheet_id");
        List<AnswerContent> answerContents = administratorService.checkAnswerInfo(answerSheetId);
        if(answerContents != null){
            res.put("code", 0);
            res.put("message", "查看成功");
            res.put("resData", answerContents);
        }else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //管理员删除答卷
    @PostMapping(value = "/deleteAnswerSheet")
    public HashMap<String, Object> deleteAnswerSheet(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer answerSheetId = obj.getInt("answer_sheet_id");
        int ret = administratorService.deleteAnswerSheet(answerSheetId);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "删除成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //管理员查看举报
    @PostMapping(value = "/checkComplaint")
    public HashMap<String, Object> checkComplaint(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        List<Complaint> complaints = administratorService.checkComplaint();
        if(complaints != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", complaints);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //管理员查看意见箱
    @PostMapping(value = "/checkSuggest")
    public HashMap<String, Object> checkSuggest(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        List<Suggest> suggests = administratorService.checkSuggest();
        if(suggests != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", suggests);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }
}
