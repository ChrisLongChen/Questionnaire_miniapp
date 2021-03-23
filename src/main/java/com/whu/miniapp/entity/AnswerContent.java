package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: 胡龙晨
 * Date: 2021-03-05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "answerContent")
public class AnswerContent implements Serializable{
    /**
     * 答卷内容表主键
     */
    private Integer id;

    /**
     * 答卷id
     */
    private Integer answerSheetId;

    /**
     * 问题id
     */
    private Integer questId;

    /**
     * 所属问卷id
     */
    private Integer questionnaireId;

    /**
     * 答案
     */
    private String answer;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getAnswerSheetId(){
        return answerSheetId;
    }

    public void setAnswerSheetId(Integer answerSheetId){
        this.answerSheetId = answerSheetId;
    }

    public Integer getQuestId(){
        return questId;
    }

    public void setQuestId(Integer questId){
        this.questId = questId;
    }

    public Integer getQuestionnaireId(){
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId){
        this.questionnaireId = questionnaireId;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
}
