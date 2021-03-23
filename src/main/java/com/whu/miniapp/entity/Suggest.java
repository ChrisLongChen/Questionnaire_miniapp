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
 * Date: 2021-01-02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "suggest")
public class Suggest implements Serializable{
    /**
     * 建议主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送时间
     */
    private DateTime sendTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUser_id(){
        return userId;
    }

    public void setUser_id(Integer user_id){
        this.userId = userId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public DateTime getSend_time(){
        return sendTime;
    }

    public void setSend_time(DateTime send_time){
        this.sendTime = sendTime;
    }

}
