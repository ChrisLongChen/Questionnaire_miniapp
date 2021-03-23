package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.Questionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */

@Mapper
public interface QuestionnaireMapper extends BaseMapper<QuestionnaireMapper>{
    List<Questionnaire> findQuestionnaire();
    List findQuestionnaireById(Integer id);
    int insertQuestionnaire(Questionnaire questionnaire);
    int updateQuestionnaire(Questionnaire questionnaire);
    Questionnaire findQuestionnaireByQuesId(Integer questionnaireId);
    int updateQuestionnaireStatu(Questionnaire questionnaire);
    List<Questionnaire> findQuestionnaireDraftByUserId(Integer userId);
    List<Questionnaire> findQuestionnaireTotalInfoByUserId(Integer userId);
    int deleteQuestionnaire(Questionnaire questionnaire);
    List<Questionnaire> findQuestionnaireByDesc();
}
