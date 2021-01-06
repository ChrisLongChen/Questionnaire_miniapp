package com.whu.miniapp.service;

import com.whu.miniapp.entity.Administrator;
import com.whu.miniapp.entity.User;
import com.whu.miniapp.mapper.AdministratorMapper;

import com.whu.miniapp.mapper.UserMapper;
import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Service
public class AdministratorService {

    @Autowired(required = false)
    private AdministratorMapper administratorMapper;

    /**
     * 管理员登录
     *
     * @param input
     * @param admin_passcode
     * @return Response
     */
    @Transactional
    public int adminLogin(String input,String admin_passcode){
        Administrator administrator = administratorMapper.findAdministratorByAdminName(input);
        if(administrator == null){
            Administrator administrator1 = administratorMapper.findAdministratorByAdminPhone(input);
            if(administrator1 == null){
                return -1;
            }
            else if(!admin_passcode.equals((administrator1.getAdmin_passcode()))){
                return -2;
            }
            else{
                return administrator1.getId();
            }
        }
        else if(!admin_passcode.equals(administrator.getAdmin_passcode())){
            Administrator administrator2 = administratorMapper.findAdministratorByAdminPhone(input);
            if(administrator2 == null){
                return -2;
            }
            else if(!admin_passcode.equals((administrator2.getAdmin_passcode()))){
                return -2;
            }
            else{
                return administrator2.getId();
            }
        }
        else{
            return administrator.getId();
        }
    }
}
