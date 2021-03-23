package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.AnswerContent;
import com.whu.miniapp.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-10
 */

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionMapper>{
    List<Question> findQuestion();
    int insertQuestion(List<Question> questions);
    List<Question> findQuestionByQuestionnaireId(Integer questionnaireId);
    List<Question> findQuestionTotalByQuestionnaireId(Integer questionnaireId);
}
