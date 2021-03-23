package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.AnswerContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-06
 */

@Mapper
public interface AnswerContentMapper extends BaseMapper<AnswerContent>{
    int insertAnswerContent(List<AnswerContent> answerContents);
    int deleteAnswerContent(Integer answerSheetId);
    List<AnswerContent> findAnswerContentByAnswerSheetId(Integer answerSheetId);

}
