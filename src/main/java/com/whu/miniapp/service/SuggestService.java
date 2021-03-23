package com.whu.miniapp.service;

import com.whu.miniapp.entity.Suggest;
import com.whu.miniapp.mapper.SuggestMapper;
import org.joda.time.DateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-01-04
 */

@Service
public class SuggestService {
    @Autowired(required = false)
    private SuggestMapper suggestMapper;

    /**
     * 查看与联系开发方
     *
     * @param userId
     * @param content
     * @return Response
     */
    @Transactional
    public int contactDesigner(Integer userId,String content){
        Suggest suggest = new Suggest();
        suggest.setUser_id(userId);
        suggest.setContent(content);
        DateTime dateTime = new DateTime();
        suggest.setSend_time(dateTime);
        int cnt = suggestMapper.insertSuggest(suggest);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }
}
