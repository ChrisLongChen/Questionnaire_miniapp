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
@TableName(value = "administrator")
public class Administrator implements Serializable{
    /**
     * 管理员表主键
     */
    private Integer id;

    /**
     * 管理员用户名
     */
    private String adminName;

    /**
     * 管理员年龄
     */
    private Integer adminAge;

    /**
     * 管理员性别
     */
    private String adminGender;

    /**
     * 管理员职业
     */
    private String adminCareer;

    /**
     * 管理员联系方式
     */
    private String adminPhone;

    /**
     * 管理员密码
     */
    private String adminPasscode;

    /**
     * 管理员微信openid
     */
    private String wechatId;

    /**
     * 管理员头像
     */
    private String wechatPic;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getAdminName(){
        return adminName;
    }

    public void setAdminName(String adminName){
        this.adminName = adminName;
    }

    public Integer getAdminAge(){
        return adminAge;
    }

    public void setAdminAge(Integer adminAge){
        this.adminAge = adminAge;
    }

    public String getAdminGender(){
        return adminGender;
    }

    public void setAdminGender(String adminGender){
        this.adminGender = adminGender;
    }

    public String getAdminCareer(){
        return adminCareer;
    }

    public void setAdminCareer(String adminCareer){
        this.adminCareer = adminCareer;
    }

    public String getAdminPhone(){
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone){
        this.adminPhone = adminPhone;
    }

    public String getAdminPasscode(){
        return adminPasscode;
    }

    public void setAdminPasscode(String adminPasscode){
        this.adminPasscode = adminPasscode;
    }

    public String getWechatId(){
        return wechatId;
    }

    public void setWechatId(String wechatId){
        this.wechatId = wechatId;
    }

    public String getWechatPic(){
        return wechatPic;
    }

    public void setWechatPic(String wechatPic){
        this.wechatPic = wechatPic;
    }
}
