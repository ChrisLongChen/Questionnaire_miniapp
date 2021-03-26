package com.whu.miniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Author: 胡龙晨
 * Date: 2021-01-02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "user")
public class User implements Serializable {
    /**
     * 用户主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPasscode;

    /**
     * 性别
     */
    private String userGender;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 职业
     */
    private String userCareer;

    /**
     * 电话
     */
    private String userPhone;

    /**
     * 地址
     */
    private String userAddr;

    /**
     * 微信号
     */
    private String wechatId;

    /**
     * 微信头像
     */
    private String wechatPic;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 禁用时长
     */
    private Integer banTime;

    /**
     * 禁用单位
     */
    private String banUnit;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 积分
     */
    private Integer point ;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserPasscode(){
        return userPasscode;
    }

    public void setUserPasscode(String userPasscode){
        this.userPasscode = userPasscode;
    }

    public String getUserGender(){
        return userGender;
    }

    public void setUserGender(String userGender){
        this.userGender = userGender;
    }

    public Integer getUserAge(){
        return userAge;
    }

    public void setUserAge(Integer userAge){
        this.userAge = userAge;
    }

    public String getUserCareer(){
        return userCareer;
    }

    public void setUserCareer(String userCareer){
        this.userCareer = userCareer;
    }

    public String getUserPhone(){
        return userPhone;
    }

    public void setUserPhone(String userPhone){
        this.userPhone = userPhone;
    }

    public String getUserAddr(){
        return userAddr;
    }

    public void setUserAddr(String userAddr){
        this.userAddr = userAddr;
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

    public Integer getUserStatus(){
        return userStatus;
    }

    public void setUserStatus(Integer userStatus){
        this.userStatus = userStatus;
    }

    public Integer getBanTime(){
        return banTime;
    }

    public void setBanTime(Integer banTime){
        this.banTime = banTime;
    }

    public String getBanUnit(){
        return banUnit;
    }

    public void setBanUnit(String banUnit){
        this.banUnit = banUnit;
    }

    public String getIntroduction(){
        return introduction;
    }

    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }

    public Integer getPoint(){
        return point;
    }

    public void setPoint(Integer point){
        this.point = point;
    }

}
