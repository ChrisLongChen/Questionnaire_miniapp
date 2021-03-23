package com.whu.miniapp.controller;

import com.whu.miniapp.entity.Question;
import com.whu.miniapp.entity.Questionnaire;
import com.whu.miniapp.entity.AnswerContent;
import com.whu.miniapp.entity.Answer;
import com.whu.miniapp.service.QuestionniareService;

import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-03-04
 */

@RestController
@RequestMapping(value = "/questionnaire",method = RequestMethod.GET)
public class QuestionnaireController {
    @Autowired
    private QuestionniareService questionnaireService;

    //用户查看问卷列表
    @PostMapping(value = "/getList")
    public HashMap<String, Object> getList(@RequestBody JSONObject obj){
        Integer userId = obj.getInt("user_id");
        List<Questionnaire> questionnaires = questionnaireService.getList(userId);
        HashMap<String, Object> res = new HashMap<>();
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

    //用户查看问卷信息
    @PostMapping(value = "/getInfo")
    public HashMap<String, Object> getInfo(@RequestBody JSONObject obj){
        Integer questionnaireId = obj.getInt("questionnaire_id");
        List questionnaire = questionnaireService.getInfo(questionnaireId);
        HashMap<String, Object> res = new HashMap<>();
        if(questionnaire != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaire);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //用户查看问卷内容
    @PostMapping(value = "/checkQues")
    public HashMap<String, Object> checkQues(@RequestBody JSONObject obj){
        Integer userId = obj.getInt("user_id");
        Integer questionnaireId = obj.getInt("questionnaire_id");
        List<Question> questions = questionnaireService.checkQues(questionnaireId);
        int answerSheetId = questionnaireService.insertAnswer(userId,questionnaireId);
        HashMap<String, Object> res = new HashMap<>();
        if(questionnaireId != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData1", questions);
            res.put("resData2", answerSheetId);
        }
        else{
            res.put("code", -1);
            res.put("message", "没有结果");
        }
        return res;
    }

    //用户删除现有草稿
    @PostMapping(value = "/deleteCraft")
    public HashMap<String, Object> deleteCraft(@RequestBody JSONObject obj){
        Integer answerSheetId = obj.getInt("answer_sheet_id");
        HashMap<String, Object> res = new HashMap<>();
        int ret = questionnaireService.deleteCraft(answerSheetId);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "删除成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "删除失败");
        }
        return res;
    }

    //用户回答问卷
    @PostMapping(value = "/answerQues")
    public HashMap<String, Object> answerQues(@RequestParam(value = "answerContent[]") List<AnswerContent> answerContents){
        HashMap<String, Object> res = new HashMap<>();
        int ret = questionnaireService.answerQues(answerContents);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户转变答卷状态
    @PostMapping(value = "/changeAnswerState")
    public HashMap<String, Object> changeAnswerState(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer answerSheetId = obj.getInt("answer_sheet_id");
        int ret = questionnaireService.changeAnswerState(answerSheetId);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "更改成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //答卷存入草稿
    @PostMapping(value = "/insertAnswerDraft")
    public HashMap<String, Object> insertAnswerDraft(@RequestParam(value = "answerContent[]") List<AnswerContent> answerContents){
        HashMap<String, Object> res = new HashMap<>();
        int ret = questionnaireService.answerQues(answerContents);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户发布问卷
    @PostMapping(value = "/issueQuestionnaire")
    public HashMap<String, Object> issueQuestionnaire(@RequestBody JSONObject obj) throws ParseException {
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        String title = obj.getString("title");
        String quesIntroduction = obj.getString("ques_introductino");
        Integer aimStartAge = obj.getInt("aim_start_age");
        Integer aimEndAge = obj.getInt("aim_end_age");
        String aimGender = obj.getString("aim_gender");
        String aimVocation = obj.getString("aim_getVocation");
        String aimLocation = obj.getString("aim_location");
        String aim_time_begin = obj.getString("aim_time_begin");
        String aim_time_end = obj.getString("aim_time_end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null; //初始化date
        Date date2 = null;
        try {
            date1 = sdf.parse(aim_time_begin); //Mon Jan 14 00:00:00 CST 2013
            date2 = sdf.parse(aim_time_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime aimTimeBegin = new DateTime(date1.getTime());
        DateTime aimTimeEnd = new DateTime(date2.getTime());
        int cnt = questionnaireService.issueQuestionnaire(userId,title,quesIntroduction,aimStartAge,aimEndAge,aimGender,aimVocation,aimLocation,aimTimeBegin,aimTimeEnd);
        if(cnt == 0){
            res.put("code", 0);
            res.put("message", "发布成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //修改问卷信息
    public HashMap<String, Object> updateQuestionnaire(@RequestBody JSONObject obj) throws ParseException {
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        String title = obj.getString("title");
        String quesIntroduction = obj.getString("ques_introductino");
        Integer aimStartAge = obj.getInt("aim_start_age");
        Integer aimEndAge = obj.getInt("aim_end_age");
        String aimGender = obj.getString("aim_gender");
        String aimVocation = obj.getString("aim_getVocation");
        String aimLocation = obj.getString("aim_location");
        String aim_time_begin = obj.getString("aim_time_begin");
        String aim_time_end = obj.getString("aim_time_end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null; //初始化date
        Date date2 = null;
        try {
            date1 = sdf.parse(aim_time_begin); //Mon Jan 14 00:00:00 CST 2013
            date2 = sdf.parse(aim_time_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime aimTimeBegin = new DateTime(date1.getTime());
        DateTime aimTimeEnd = new DateTime(date2.getTime());
        int cnt = questionnaireService.updateQuestionnaire(questionnaireId,title,quesIntroduction,aimStartAge,aimEndAge,aimGender,aimVocation,aimLocation,aimTimeBegin,aimTimeEnd);
        if(cnt == 0){
            res.put("code", 0);
            res.put("message", "修改成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户制作问卷问题
    public HashMap<String, Object> issueQuest(@RequestParam(value = "question[]") List<Question> questions){
        HashMap<String, Object> res = new HashMap<>();
        int ret = questionnaireService.issueQuest(questions);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户转变问卷状态
    public HashMap<String, Object> changeQuestionState(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        int ret = questionnaireService.changeQuestionState(questionnaireId);
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

    //问卷存入草稿
    @PostMapping(value = "/issueQuestDraft")
    public HashMap<String, Object> issueQuestDraft(@RequestParam(value = "question[]") List<Question> questions){
        HashMap<String, Object> res = new HashMap<>();
        int ret = questionnaireService.issueQuest(questions);
        if(ret == 0){
            res.put("code", 0);
            res.put("message", "成功");
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //从草稿中提取问卷列表
    @PostMapping(value = "/drawQuestionnaire")
    public HashMap<String, Object> drawQuestionnaire(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        List<Questionnaire> questionnaires = questionnaireService.drawQuestionnaire(userId);
        if(questionnaires != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaires);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //从草稿中提取问卷信息
    @PostMapping(value = "/drawQuestInfo")
    public HashMap<String, Object> drawQuestInfo(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        Questionnaire questionnaire = questionnaireService.drawQuestInfo(questionnaireId);
        if(questionnaire != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaire);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //从草稿中提取问题设计
    @PostMapping(value = "/drawQuestion")
    public HashMap<String, Object> drawQuestion(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        List<Question> questions = questionnaireService.drawQuestion(questionnaireId);
        if(questions != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questions);
        }
        else{
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //从草稿中提取答卷
    @PostMapping(value = "/drawAnswerSheet")
    public HashMap<String, Object> drawAnswerSheet(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        List<Answer> answers = questionnaireService.drawAnswerSheet(userId);
        if(answers != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", answers);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //从草稿中提取答卷信息
    @PostMapping(value = "/drawAnswerContent")
    public HashMap<String, Object> drawAnswerContent(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer answerSheetId = obj.getInt("user_id");
        List<AnswerContent> answerContents = questionnaireService.drawAnswerContent(answerSheetId);
        if(answerContents != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", answerContents);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户查看自己发布的问卷
    @PostMapping(value = "/checkMyQuestionnaire")
    public HashMap<String, Object> checkMyQuestionnaire(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        List<Questionnaire> questionnaires = questionnaireService.checkMyQuestionnaire(userId);
        if(questionnaires != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questionnaires);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户查看已回收的问卷信息
    @PostMapping(value = "/checkCycledQuestionnaire")
    public HashMap<String, Object> checkCycledQuetionnaire(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        List<Question> questions = questionnaireService.checkCycledQuestionnaireInfo(questionnaireId);
        if(questions != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", questions);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户删除自己发布的问卷
    @PostMapping(value = "/deleteMyQuestionnaire")
    public HashMap<String, Object> deleteMyQuestionnaire(@RequestBody JSONObject obj) {
        HashMap<String, Object> res = new HashMap<>();
        Integer questionnaireId = obj.getInt("questionnaire_id");
        int ret = questionnaireService.deleteMyQuestionnaire(questionnaireId);
        if (ret == 0) {
            res.put("code", 0);
            res.put("message", "删除成功");
        } else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户查看自己的答卷
    @PostMapping(value = "/checkMyAnswerSheet")
    public HashMap<String, Object> checkMyAnswerSheet(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer userId = obj.getInt("user_id");
        List<Answer> answers = questionnaireService.checkMyAnswerSheet(userId);
        if(answers != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", answers);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }

    //用户查看自己答卷信息
    @PostMapping(value = "/checkMyAnswer")
    public HashMap<String, Object> checkMyAnswer(@RequestBody JSONObject obj){
        HashMap<String, Object> res = new HashMap<>();
        Integer answerSheetId = obj.getInt("answer_sheet_id");
        List<AnswerContent> answerContents = questionnaireService.checkMyAnswer(answerSheetId);
        if(answerContents != null){
            res.put("code", 0);
            res.put("message", "获取成功");
            res.put("resData", answerContents);
        }
        else {
            res.put("code", -1);
            res.put("message", "失败");
        }
        return res;
    }
}
