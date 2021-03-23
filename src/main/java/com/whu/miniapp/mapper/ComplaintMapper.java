package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.Answer;
import com.whu.miniapp.entity.Complaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-10
 */

@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint>{
    int insertComplaint(Complaint complaint);
}
