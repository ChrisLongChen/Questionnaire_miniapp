package com.whu.miniapp.service;

import com.whu.miniapp.entity.*;
import com.whu.miniapp.mapper.QuestionnaireMapper;
import com.whu.miniapp.mapper.AdministratorMapper;
import com.whu.miniapp.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-03-13
 */

@Service
public class AdministratorService {
    @Autowired(required = false)
    private AdministratorMapper administratorMapper;

    @Autowired(required = false)
    private QuestionnaireMapper questionnaireMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 修改管理员信息
     *
     * @param adminId
     * @param adminName
     * @param adminAge
     * @param adminGender
     * @param adminCareer
     * @return Response
     */
    public int modifyAdminInfo(Integer adminId, @Nullable String adminName, @Nullable Integer adminAge, @Nullable String adminGender, @Nullable String adminCareer){
        Administrator administrator = administratorMapper.findAdminById(adminId);
        administrator.setAdminName(adminName);
        administrator.setAdminAge(adminAge);
        administrator.setAdminGender(adminGender);
        administrator.setAdminCareer(adminCareer);
        int cnt = administratorMapper.updateAdminInfo(administrator);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 管理员查看问卷列表
     *
     * @return Response
     */
    @Transactional
    public List<Questionnaire> adminGetList(){
        List<Questionnaire> questionnaires = questionnaireMapper.findQuestionnaireByDesc();
        return questionnaires;
    }

    /**
     * 管理员删除问卷
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public int adminDeleteQuestionnaire(Integer questionnaireId){
        int cnt = administratorMapper.deleteQuestionnaireById(questionnaireId);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 管理员查看用户列表
     *
     * @return Response
     */
    @Transactional
    public List<User> checkUser(){
        List<User> users = userMapper.findUserByAsc();
        return users;
    }

    /**
     * 管理员搜索用户
     *
     * @param input
     * @return Response
     */
    @Transactional
    public List<User> searchUser(String input){
        List<User> users1 = userMapper.findUserListByUsername(input);
        List<User> users2 = userMapper.findUserListByUserPhone(input);
        for (User menuEntity : users1) {
            Iterator<User> iterator = users2.iterator();
            while (iterator.hasNext()) {
                User next = iterator.next();
                if (next.getId().equals(menuEntity.getId())) {
                    iterator.remove();
                }
            }
        }
        users1.addAll(users2);
        return users1;
    }

    /**
     * 管理员查看用户信息
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public User checkUserInfo(Integer userId){
        User user = administratorMapper.findUserById(userId);
        return user;
    }

    /**
     * 管理员查看某用户发布的问卷
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public List<Questionnaire> checkUserQuestionnaire(Integer userId){
        List<Questionnaire> questionnaires = administratorMapper.findQuestionnaireByUserId(userId);
        return questionnaires;
    }

    /**
     * 管理员封禁用户
     *
     * @param userId
     * @param banUnit
     * @param banTime
     * @return Response
     */
    @Transactional
    public int banUser(Integer userId,String banUnit,Integer banTime){
        User user = administratorMapper.findUserById(userId);
        user.setBanUnit(banUnit);
        user.setBanTime(banTime);
        int cnt = administratorMapper.updateUserBanInfo(user);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 管理员查看答卷列表
     *
     * @return Response
     */
    @Transactional
    public List<Answer> checkAnswerList(){
        List<Answer> answers = administratorMapper.findAnswerByIdOrder();
        return answers;
    }

    /**
     * 管理员查看答卷详情
     *
     * @param answerSheetId
     * @return Response
     */
    public List<AnswerContent> checkAnswerInfo(Integer answerSheetId){
        List<AnswerContent> answerContents = administratorMapper.findAnswerContentByAnswerSheetId(answerSheetId);
        return answerContents;
    }

    /**
     * 管理员删除答卷
     *
     * @param answerSheetId
     * @return Response
     */
    public int deleteAnswerSheet (Integer answerSheetId){
        Answer answer = administratorMapper.findAnswerByAnswerSheetId(answerSheetId);
        int cnt1 = administratorMapper.deleteAnswerByAnswerSheetId(answerSheetId);
        int cnt2 = administratorMapper.deleteAnswerContentByAnswerSheetId(answerSheetId);
        if(cnt1 >= 0){
            if( cnt2 >= 0){
                return 0;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }

    /**
     * 管理员查看举报
     *
     * @return Response
     */
    public List<Complaint> checkComplaint(){
        List<Complaint> complaints = administratorMapper.findComplaintByIdOrder();
        return complaints;
    }

    /**
     * 管理员查看意见箱
     *
     * @return Response
     */
    public List<Suggest> checkSuggest(){
        List<Suggest> suggests = administratorMapper.findSuggestByIdOrder();
        return suggests;
    }

}
