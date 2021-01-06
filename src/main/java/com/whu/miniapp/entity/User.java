package com.whu.miniapp.entity;

import javax.persistence.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-02
 */

@Entity
@Table(name = "user")
public class User {
    @Id//主键注解
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增
    @Column(name = "id")//对应表中的列
    private Integer id;
    @Column(name = "user_name")
    private String user_name; //用户名
    @Column(name = "user_passcode")
    private String user_passcode;//密码
    @Column(name = "user_gender")
    private String user_gender;//性别
    @Column(name = "user_age")
    private Integer user_age;//年龄
    @Column(name = "user_career")
    private String user_career;//职业
    @Column(name = "user_phone")
    private String user_phone;//电话
    @Column(name = "user_addr")
    private String user_addr;//地址
    @Column(name = "wechat_id")
    private String wechat_id;//微信号
    @Column(name = "wechat_pic")
    private String wechat_pic;//微信头像
    @Column(name = "user_status")
    private Integer user_status;//用户状态
    @Column(name = "ban_time")
    private Integer ban_time;//禁用时长
    @Column(name = "ban_unit")
    private String ban_unit;//禁用单位
    @Column(name = "introduction")
    private String introduction;//个人简介
    @Column(name = "point")
    private Integer point ;//积分

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public String getUser_passcode(){
        return user_passcode;
    }

    public void setUser_passcode(String user_passcode){
        this.user_passcode = user_passcode;
    }

    public String getUser_gender(){
        return user_gender;
    }

    public void setUser_gender(String user_gender){
        this.user_gender = user_gender;
    }

    public Integer getUser_age(){
        return user_age;
    }

    public void setUser_age(Integer user_age){
        this.user_age = user_age;
    }

    public String getUser_career(){
        return user_career;
    }

    public void setUser_career(String user_career){
        this.user_career = user_career;
    }

    public String getUser_phone(){
        return user_phone;
    }

    public void setUser_phone(String user_phone){
        this.user_phone = user_phone;
    }

    public String getUser_addr(){
        return user_addr;
    }

    public void setUser_addr(String user_addr){
        this.user_addr = user_addr;
    }

    public String getWechat_id(){
        return wechat_id;
    }

    public void setWechat_id(String wechat_id){
        this.wechat_id = wechat_id;
    }

    public String getWechat_pic(){
        return wechat_pic;
    }

    public void setWechat_pic(String wechat_pic){
        this.wechat_pic = wechat_pic;
    }

    public Integer getUser_status(){
        return user_status;
    }

    public void setUser_status(Integer user_status){
        this.user_status = user_status;
    }

    public Integer getBan_time(){
        return ban_time;
    }

    public void setBan_time(Integer ban_time){
        this.ban_time = ban_time;
    }

    public String getBan_unit(){
        return ban_unit;
    }

    public void setBan_unit(String ban_unit){
        this.ban_unit = ban_unit;
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
