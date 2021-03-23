package com.whu.miniapp.service;

import com.whu.miniapp.entity.Complaint;
import com.whu.miniapp.mapper.ComplaintMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: 胡龙晨
 * Date: 2021-03-02
 */

@Service
public class ComplaintService {
    @Autowired(required = false)
    private ComplaintMapper complaintMapper;

    /**
     * 用户举报问卷
     *
     * @param userId
     * @param questionnaireId
     * @param reason
     * @return Response
     */
    @Transactional
    public int reportQues(Integer userId,Integer questionnaireId,String reason){
        Complaint complaint =new Complaint();
        complaint.setUserId(userId);
        complaint.setQuestionnaireId(questionnaireId);
        complaint.setReason(reason);
        int cnt = complaintMapper.insertComplaint(complaint);
        if(cnt >= 0){
            return 0;
        }
        else{
            return -1;
        }
    }
}
