package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: 胡龙晨
 * Date: 2021-03-10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "complaint")
public class Complaint implements Serializable{
    /**
     * 举报表主键
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
     * 举报理由
     */
    private String reason;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer user_id){
        this.userId = userId;
    }

    public Integer getQuestionnaireId(){
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId){
        this.questionnaireId = questionnaireId;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

}
