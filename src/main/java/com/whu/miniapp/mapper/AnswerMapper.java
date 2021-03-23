package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-10
 */

@Mapper
public interface AnswerMapper extends BaseMapper<Answer>{
    int insertAnswer(Answer answer);
    Answer findAnswerById(Integer answerSheetId);
    int updateAnswerStatu(Answer answer);
    List<Answer> findAnswerDraftByUserId(Integer userId);
    List<Answer> findAnswerByUserId(Integer userId);
}
