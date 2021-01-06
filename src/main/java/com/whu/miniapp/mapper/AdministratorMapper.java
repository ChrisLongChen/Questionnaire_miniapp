package com.whu.miniapp.mapper;

import com.whu.miniapp.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Mapper
public interface AdministratorMapper {
    int insertAdministrator(Administrator administrator);
    Administrator findAdministratorByAdminName(String admin_name);
    Administrator findAdministratorByAdminPhone(String admin_phone);
}
