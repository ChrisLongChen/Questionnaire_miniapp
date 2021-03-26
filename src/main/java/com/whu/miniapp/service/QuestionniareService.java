package com.whu.miniapp.service;

import com.whu.miniapp.entity.Questionnaire;
import com.whu.miniapp.entity.Question;
import com.whu.miniapp.entity.User;
import com.whu.miniapp.entity.Answer;
import com.whu.miniapp.entity.AnswerContent;
import com.whu.miniapp.mapper.*;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */

@Service
public class QuestionniareService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionnaireMapper questionnaireMapper;

    @Autowired(required = false)
    private AnswerMapper answerMapper;

    @Autowired(required = false)
    private AnswerContentMapper answerContentMapper;

    @Autowired(required = false)
    private ComplaintMapper complaintMapper;

    /**
     * 用户查看问卷列表（有问题，需要算法）
     *
     * @param userId
     * @return Response
     */

    //此处需要设计问卷推荐方案，暂时直接推送全部
    @Transactional
    public List<Questionnaire> getList(Integer userId){
        User user = userMapper.findUserByUserId(userId);
        List<Questionnaire> questionnaires = questionnaireMapper.findQuestionnaire();
        return questionnaires;
    }

    /**
     * 用户查看问卷信息
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public List getInfo(Integer questionnaireId){
        List questionnaire = questionnaireMapper.findQuestionnaireById(questionnaireId);
        return questionnaire;
    }

    /**
     * 用户查看问卷内容
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public List<Question> checkQues(Integer questionnaireId){
        List<Question> questions = questionMapper.findQuestion();
        return questions;
    }

    /**
     * 添加答卷（在查看问卷内容同时进行）
     *
     * @param userId
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public int insertAnswer(Integer userId,Integer questionnaireId){
        Answer answer = new Answer();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        answer.setUserId(userId);
        answer.setQuestionnaireId(questionnaireId);
        answer.setAnswerTime(timestamp);
        answer.setAnsStatus(0);
        int ret = answerMapper.insertAnswer(answer);
        if(ret >= 0 ){
            return answer.getId();
        }
        else {
            return -1;
        }
    }

    /**
     * 用户删除现有草稿（在其他操作间隔中进行）
     *
     * @param answerSheetId
     * @return Response
     */
    @Transactional
    public int deleteCraft(Integer answerSheetId){
        int cnt = answerContentMapper.deleteAnswerContent(answerSheetId);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 用户回答问卷（有问题）
     *
     * @param answerContents
     * @return Response
     */
    @Transactional
    public int answerQues(List<AnswerContent> answerContents){
        int cnt = answerContentMapper.insertAnswerContent(answerContents);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 将草稿转为答卷
     *
     * @param answerSheetId
     * @return Response
     */
    @Transactional
    public int changeAnswerState(Integer answerSheetId){
        Answer answer = answerMapper.findAnswerById(answerSheetId);
        answer.setAnsStatus(1);
        int cnt = answerMapper.updateAnswerStatu(answer);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }


    /**
     * 用户发布问卷
     *
     * @param userId
     * @param title
     * @param quesIntroduction
     * @param aimStartAge
     * @param aimEndAge
     * @param aimGender
     * @param aimVocation
     * @param aimLocation
     * @param aimTimeBegin
     * @param aimTimeEnd
     * @return Response
     */
    @Transactional
    public int issueQuestionnaire(Integer userId,String title,String quesIntroduction,Integer aimStartAge,Integer aimEndAge,String aimGender,String aimVocation,String aimLocation,Timestamp aimTimeBegin,Timestamp aimTimeEnd){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setUserId(userId);
        questionnaire.setTitle(title);
        questionnaire.setQuesIntroduction(quesIntroduction);
        questionnaire.setAimStartAge(aimStartAge);
        questionnaire.setAimEndAge(aimEndAge);
        questionnaire.setAimGender(aimGender);
        questionnaire.setAimVocation(aimVocation);
        questionnaire.setAimLocation(aimLocation);
        questionnaire.setAimTimeBegin(aimTimeBegin);
        questionnaire.setAimTimeEnd(aimTimeEnd);
        questionnaire.setIssStatus(0);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        questionnaire.setIssTime(timestamp);
        questionnaire.setQuestNumber(0);
        int cnt = questionnaireMapper.insertQuestionnaire(questionnaire);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 用户修改问卷信息
     *
     * @param questionnaireId
     * @param title
     * @param quesIntroduction
     * @param aimStartAge
     * @param aimEndAge
     * @param aimGender
     * @param aimVocation
     * @param aimLocation
     * @param aimTimeBegin
     * @param aimTimeEnd
     * @return Response
     */
    @Transactional
    public int updateQuestionnaire(Integer questionnaireId,String title,String quesIntroduction,Integer aimStartAge,Integer aimEndAge,String aimGender,String aimVocation,String aimLocation,Timestamp aimTimeBegin,Timestamp aimTimeEnd){
        Questionnaire questionnaire = questionnaireMapper.findQuestionnaireByQuesId(questionnaireId);
        questionnaire.setTitle(title);
        questionnaire.setQuesIntroduction(quesIntroduction);
        questionnaire.setAimStartAge(aimStartAge);
        questionnaire.setAimEndAge(aimEndAge);
        questionnaire.setAimGender(aimGender);
        questionnaire.setAimVocation(aimVocation);
        questionnaire.setAimLocation(aimLocation);
        questionnaire.setAimTimeBegin(aimTimeBegin);
        questionnaire.setAimTimeEnd(aimTimeEnd);
        int cnt = questionnaireMapper.updateQuestionnaire(questionnaire);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 用户制作问卷问题（有问题）
     *
     * @param questions
     * @return Response
     */
    @Transactional
    public int issueQuest(List<Question> questions){
        int cnt = questionMapper.insertQuestion(questions);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 将草稿转为问卷
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public int changeQuestionState(Integer questionnaireId){
        Questionnaire questionnaire = questionnaireMapper.findQuestionnaireByQuesId(questionnaireId);
        questionnaire.setIssStatus(1);
        int cnt = questionnaireMapper.updateQuestionnaireStatu(questionnaire);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 从草稿中提取问卷列表
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public List<Questionnaire> drawQuestionnaire(Integer userId){
        List<Questionnaire> questionnaires = questionnaireMapper.findQuestionnaireDraftByUserId(userId);
        return questionnaires;
    }

    /**
     * 从草稿中提取问卷信息
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public Questionnaire drawQuestInfo(Integer questionnaireId){
        Questionnaire questionnaire = questionnaireMapper.findQuestionnaireByQuesId(questionnaireId);
        return questionnaire;
    }

    /**
     * 从草稿中提取问卷信息
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public List<Question> drawQuestion(Integer questionnaireId){
        List<Question> questions = questionMapper.findQuestionByQuestionnaireId(questionnaireId);
        return questions;
    }

    /**
     * 从草稿中提取答卷列表
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public List<Answer> drawAnswerSheet(Integer userId){
        List<Answer> answers = answerMapper.findAnswerDraftByUserId(userId);
        return answers;
    }

    /**
     * 从草稿中提取答卷信息
     *
     * @param answerSheetId
     * @return Response
     */
    @Transactional
    public List<AnswerContent> drawAnswerContent(Integer answerSheetId){
        List<AnswerContent> answerContents = answerContentMapper.findAnswerContentByAnswerSheetId(answerSheetId);
        return answerContents;
    }

    /**
     * 用户查看自己发布的问卷
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public List<Questionnaire> checkMyQuestionnaire(Integer userId){
        List<Questionnaire> questionnaires = questionnaireMapper.findQuestionnaireDraftByUserId(userId);
        return questionnaires;
    }

    /**
     * 用户查看已回收的问卷信息
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public List<Question> checkCycledQuestionnaireInfo(Integer questionnaireId){
        List<Question> questions = questionMapper.findQuestionTotalByQuestionnaireId(questionnaireId);
        return questions;
    }

    /**
     * 用户查看自己发布的问卷
     *
     * @param questionnaireId
     * @return Response
     */
    @Transactional
    public int deleteMyQuestionnaire(Integer questionnaireId){
        Questionnaire questionnaire = questionnaireMapper.findQuestionnaireByQuesId(questionnaireId);
        int cnt = questionnaireMapper.deleteQuestionnaire(questionnaire);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 用户查看自己的答卷
     *
     * @param userId
     * @return Response
     */
    @Transactional
    public List<Answer> checkMyAnswerSheet(Integer userId){
        List<Answer> answers = answerMapper.findAnswerByUserId(userId);
        return answers;
    }

    /**
     * 用户查看自己的答卷信息
     *
     * @param answerSheetId
     * @return Response
     */
    @Transactional
    public List<AnswerContent> checkMyAnswer(Integer answerSheetId){
        List<AnswerContent> answerContents = answerContentMapper.findAnswerContentByAnswerSheetId(answerSheetId);
        return answerContents;
    }
}
