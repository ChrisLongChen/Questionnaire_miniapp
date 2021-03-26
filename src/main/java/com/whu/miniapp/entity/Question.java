package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "question")
public class Question implements Serializable{
    /**
     * 问题表主键
     */
    private Integer id;

    /**
     * 问卷id
     */
    private Integer questionnaireId;

    /**
     * 问题类型
     */
    private Integer questType;

    /**
     * 问题内容
     */
    private String questContent;

    /**
     * 统计信息
     */
    private String remark;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getQuestionnaireId(){
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId){
        this.questionnaireId = questionnaireId;
    }

    public Integer getQuestType(){
        return questType;
    }

    public void setQuestType(Integer questType){
        this.questType = questType;
    }

    public String getQuestContent(){
        return questContent;
    }

    public void setQuestContent(String questContent){
        this.questContent = questContent;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }
}
