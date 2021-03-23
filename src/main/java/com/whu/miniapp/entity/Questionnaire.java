package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "questionnaire")
public class Questionnaire implements Serializable{
    /**
     * 问卷表主键
     */
    private Integer id;

    /**
     * 建议主键
     */
    private Integer userId;

    /**
     * 问卷题目
     */
    private String title;

    /**
     * 问卷介绍
     */
    private String quesIntroduction;

    /**
     * 目标起始年龄
     */
    private Integer aimStartAge;

    /**
     * 目标终止年龄
     */
    private Integer aimEndAge;

    /**
     * 目标性别
     */
    private String aimGender;

    /**
     * 目标职业
     */
    private String aimVocation;

    /**
     * 目标地址
     */
    private String aimLocation;

    /**
     * 问卷开始时间
     */
    private DateTime aimTimeBegin;

    /**
     * 问卷结束时间
     */
    private DateTime aimTimeEnd;

    /**
     * 发布状态
     */
    private Integer issStatus;

    /**
     * 发布时间
     */
    private DateTime issTime;

    /**
     * 问题数目
     */
    private Integer questNumber;

    /**
     * 所收答卷数目
     */
    private Integer answerSheetNumber;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getQuesIntroduction(){
        return quesIntroduction;
    }

    public void setQuesIntroduction(String introduction){
        this.quesIntroduction = quesIntroduction;
    }

    public Integer getAimStartAge(){
        return aimStartAge;
    }

    public void setAimStartAge(Integer aimStartAge){
        this.aimStartAge = aimStartAge;
    }

    public Integer getAimEndAge(){
        return aimEndAge;
    }

    public void setAimEndAge(Integer aimEndAge){
        this.aimEndAge = aimEndAge;
    }

    public String getAimGender(){
        return aimGender;
    }

    public void setAimGender(String aimGender){
        this.aimGender = aimGender;
    }

    public String getAimVocation(){
        return aimVocation;
    }

    public void setAimVocation(String aimVocation){
        this.aimVocation = aimVocation;
    }

    public String getAimLocation(){
        return aimLocation;
    }

    public void setAimLocation(String aimLocation){
        this.aimLocation = aimLocation;
    }

    public DateTime getAimTimeBegin(){
        return aimTimeBegin;
    }

    public void setAimTimeBegin(DateTime aimTimeBegin){
        this.aimTimeBegin = aimTimeBegin;
    }

    public DateTime getAimTimeEnd(){
        return aimTimeEnd;
    }

    public void setAimTimeEnd(DateTime aimTimeEnd){
        this.aimTimeEnd = aimTimeEnd;
    }

    public Integer getIssStatus(){
        return issStatus;
    }

    public void setIssStatus(Integer issStatus){
        this.issStatus = issStatus;
    }

    public DateTime getIssTime(){
        return issTime;
    }

    public void setIssTime(DateTime issTime){
        this.issTime = issTime;
    }

    public Integer getQuestNumber(){
        return questNumber;
    }

    public void setQuestNumber(Integer questNumber){
        this.questNumber = questNumber;
    }

    public Integer getAnswerSheetNumber(){
        return answerSheetNumber;
    }

    public void setAnswerSheetNumber(Integer answerSheetNumber){
        this.answerSheetNumber = answerSheetNumber;
    }


}
