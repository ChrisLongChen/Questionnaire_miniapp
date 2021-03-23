package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.Suggest;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */

@Mapper
public interface SuggestMapper extends BaseMapper<Suggest>{
    int insertSuggest(Suggest suggest);
}
