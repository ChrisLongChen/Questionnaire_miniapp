package com.whu.miniapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.miniapp.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 胡龙晨
 * Date: 2021-03-10
 */

@Mapper
public interface AdministratorMapper extends BaseMapper<Administrator>{
    int deleteQuestionnaireById(Integer questionnaireId);
    int updateAdminInfo(Administrator administrator);
    Administrator findAdminById(Integer adminId);
    User findUserById(Integer userId);
    List<Questionnaire> findQuestionnaireByUserId(Integer userId);
    int updateUserBanInfo(User user);
    List<Answer> findAnswerByIdOrder();
    List<AnswerContent> findAnswerContentByAnswerSheetId(Integer answerSheetId);
    Answer findAnswerByAnswerSheetId(Integer answerSheetId);
    int deleteAnswerByAnswerSheetId(Integer answerSheetId);
    int deleteAnswerContentByAnswerSheetId(Integer answerSheetId);
    List<Complaint> findComplaintByIdOrder();
    List<Suggest> findSuggestByIdOrder();
}
