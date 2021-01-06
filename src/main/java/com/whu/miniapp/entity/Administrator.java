package com.whu.miniapp.entity;

import javax.persistence.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Entity
@Table(name = "administrator")
public class Administrator {
    @Id//主键注解
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增
    @Column(name = "id")//对应表中的列
    private Integer id;
    @Column(name = "admin_name")
    private String admin_name; //管理员用户名
    @Column(name = "admin_age")
    private Integer admin_age;//管理员年龄
    @Column(name = "admin_gender")
    private String admin_gender;//管理员性别
    @Column(name = "admin_career")
    private String admin_career;//管理员职业
    @Column(name = "admin_phone")
    private String admin_phone;//管理员电话
    @Column(name = "admin_passcode")
    private String admin_passcode;//管理员密码
    @Column(name = "wechat_id")
    private String wechat_id;//微信号
    @Column(name = "wechat_pic")
    private String wechat_pic;//微信头像

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getAdmin_name(){
        return admin_name;
    }

    public void setAdmin_name(String admin_name){
        this.admin_name = admin_name;
    }

    public Integer getAdmin_age(){
        return admin_age;
    }

    public void setAdmin_age(Integer admin_age){
        this.admin_age = admin_age;
    }

    public String getAdmin_gender(){
        return admin_gender;
    }

    public void setAdmin_gender(String admin_gender){
        this.admin_gender = admin_gender;
    }

    public String getAdmin_career(){
        return admin_career;
    }

    public void setAdmin_career(String admin_career){
        this.admin_career = admin_career;
    }

    public String getAdmin_phone(){
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone){
        this.admin_phone = admin_phone;
    }

    public String getAdmin_passcode(){
        return admin_passcode;
    }

    public void setAdmin_passcode(String admin_passcode){
        this.admin_passcode = admin_passcode;
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
}

