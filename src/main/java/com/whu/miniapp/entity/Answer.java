package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: 胡龙晨
 * Date: 2021-03-05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "answer")
public class Answer implements Serializable{
    /**
     * 答卷表主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 问卷id
     */
    private Integer questionnaireId;

    /**
     * 答题时间
     */
    private Timestamp answerTime;

    /**
     * 答题时间
     */
    private Integer ansStatus;

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

    public Integer getQuestionnaireId(){
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId){
        this.questionnaireId = questionnaireId;
    }

    public Timestamp getAnswerTime(){
        return answerTime;
    }

    public void setAnswerTime(Timestamp answerTime){
        this.answerTime = answerTime;
    }

    public Integer getAnsStatus(){
        return ansStatus;
    }

    public void setAnsStatus(Integer ansStatus){
        this.ansStatus = ansStatus;
    }
}

