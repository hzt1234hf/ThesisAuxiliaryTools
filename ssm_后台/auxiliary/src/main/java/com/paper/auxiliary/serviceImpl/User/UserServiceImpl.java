package com.paper.auxiliary.serviceImpl.User;

import com.paper.auxiliary.entity.user.*;
import com.paper.auxiliary.repository.User.*;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private User_InfoRepository user_infoRepository;
    @Autowired
    private User_LogRepository user_logRepository;
    @Autowired
    private User_FeedbackRepository user_feedbackRepository;
    @Autowired
    private User_InputRepository user_inputRepository;
    @Autowired
    private User_OutputRepository user_outputRepository;
    @Autowired
    private Analyse_UserRepository analyse_userRepository;


    // ################ 用户信息操作 ################


    @Override
    public Integer getUserInfoCount() {
        return user_infoRepository.getUserInfoCount();
    }
    @Override
    public List<User_Info> getUserInfoScope(Integer index_1, Integer index_2) {
        return user_infoRepository.getUserInfoScope(index_1,index_2);
    }

    @Override
    public List<User_Info> getUserInfoAll() {
        return user_infoRepository.findAll();
    }


    @Override
    public User_Info getUserInfoByOid(String oid) {
        return user_infoRepository.findById(oid).orElse(null);
    }

    @Override
    public User_Info addUserInfo(User_Info user_Info) {
        return user_infoRepository.save(user_Info);
    }

    @Override
    public User_Info updateUserInfo(User_Info user_Info) {
        return user_infoRepository.save(user_Info);
    }

    @Override
    public Integer updateUserInfoDate(String oid) {
        return user_infoRepository.updateUserInfoDate(oid);
    }

    @Override
    public void delUserInfo(String oid) {
        user_infoRepository.deleteById(oid);
    }

// ################ 用户日志操作 ################

    @Override
    public User_Log addUserLog(User_Log ul) {
        return user_logRepository.save(ul);
    }

    @Override
    public List<User_Log> getUserLogAll() {
        return user_logRepository.findAll();
    }

    @Override
    public List<User_Log> getAllUserLogByOid(String oid) {
        List<User_Log> user_logs = user_logRepository.findByOpenId(oid);
        return user_logs;
    }

    @Override
    public List<User_Log> getCountUserLogByOpenId(String oid) {
        return user_logRepository.findCountByOpenId(oid,PageRequest.of(0,20)).getContent();
    }

    @Override
    public List<User_Log> getUserLogByMid(Integer mid) {
        return user_logRepository.findByModuleId(mid);
    }

    @Override
    public BigInteger getUserLogByModuleIdAndDate(Integer mid, String date) {
        return user_logRepository.findByModuleIdAndDate(mid,date);
    }

    @Override
    public BigInteger getAllCountByDate(String date) {
        return user_logRepository.findAllByDate(date);
    }

    @Override
    public BigInteger getAllCountFromDate(String date) {
        return user_logRepository.findAllFromDate(date);
    }

    @Override
    public List<Object[]> getUserLogByDate(String date) {
        return user_logRepository.findByDate(date);
    }

    @Override
    public List<Object[]> getUserLogFromDate(String date) {
        return user_logRepository.findFromDate(date);
    }

    @Override
    public User_Log getOneIsNotFinished() {
        return user_logRepository.findOneIsNotFinished();
    }

    @Override
    public Integer getIsNotFinishedCount() {
        return user_logRepository.getIsNotFinishedCount();
    }

    @Override
    public Integer updateStatusByLogId(Integer lid) {
        return user_logRepository.updateStatusByLogId(lid);
    }

    @Override
    public void delUserLogById(Integer lid) {
        user_logRepository.deleteById(lid);
    }

    @Override
    public void delUserLogByOid(String oid) {
        user_logRepository.deleteByOpenId(oid);
    }

    @Override
    public void delUserLogByMid(Integer mid) {
        user_logRepository.deleteByModuleId(mid);
    }

    @Override
    public void delUserLogByOidAndMid(Integer oid, Integer mid) {
        user_logRepository.deleteByOpenIdAndModuleId(oid,mid);
    }
    // ################ 用户反馈操作 ################

    @Override
    public List<User_Feedback> getUserFeedbackAll() {
        return user_feedbackRepository.findAll();
    }

    @Override
    public User_Feedback getUserFeedbackByFid(Integer fid) {
        return user_feedbackRepository.findById(fid).get();
    }

    @Override
    public User_Feedback addUserFeedback(User_Feedback user_feedback) {
        return user_feedbackRepository.save(user_feedback);
    }

    @Override
    public void delUserFeedbackByFid(Integer fid) {
        user_feedbackRepository.deleteById(fid);
    }
    @Override
    public void delUserFeedbackByOpenid(Integer openid) {
        user_feedbackRepository.deleteByOpenid(openid);
    }

    @Override
    public int getUserFeedbackCount() {
        return user_feedbackRepository.getUserFeedbackCount();
    }

    @Override
    public List<User_Feedback> getUserFeedbackScope(Integer index_1, Integer index_2) {
        return user_feedbackRepository.getUserFeedbackScope(index_1,index_2);
    }

    @Override
    public int delUserFeedbackByFids(List<Integer> fids) {
        return user_feedbackRepository.deleteUserFeedbackByFids(fids);
    }
    // ################ 用户数据输入操作 ################

    @Override
    public List<User_Input> getUserInputAll() {
        return user_inputRepository.findAll();
    }

    @Override
    public List<User_Input> getUserInputAllByLid(Integer logId) {
        return user_inputRepository.findByLogId(logId);
    }

    @Override
    public User_Input addUserInput(User_Input user_Input) {
        return user_inputRepository.save(user_Input);
    }

    @Override
    public void delUserInputById(Integer inId) {
        user_inputRepository.deleteById(inId);
    }

    @Override
    public void delUserInputByLid(Integer logId) {
        user_inputRepository.deleteByLogId(logId);
    }

    // ################ 用户数据输出操作 ################
    @Override
    public List<User_Output> getUserOutputAll() {
        return user_outputRepository.findAll();
    }

    @Override
    public List<User_Output> getUserOutputAllByLid(Integer logId) {
        return user_outputRepository.findByLogId(logId);
    }

    @Override
    public User_Output addUserOutput(User_Output user_Output) {
        return user_outputRepository.save(user_Output);
    }

    @Override
    public void delUserOutputById(Integer outId) {
        user_outputRepository.deleteById(outId);
    }

    @Override
    public void delUserOutputByLid(Integer logid) {
        user_outputRepository.deleteByLogId(logid);
    }
    // ################ 用户分析操作 ################

    @Override
    public List<Analyse_User> getAnalyseUserAll() {
        return analyse_userRepository.findAll();
    }

    @Override
    public List<Map<String,String>> getAnalyseUserFromDate(String date) {
        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
        List<Object[]> list = analyse_userRepository.findFromDate(date);
        for(Object[] iter : list)
        {
            Map<String,String> map = new HashMap<String,String>();
            map.put("openId",(String)iter[0]);
            map.put("wxAccount",(String)iter[1]);
            map.put("value",((BigDecimal)iter[2]).toString());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public Analyse_User getAnalyseUserAllById(Integer id) {
        return analyse_userRepository.findById(id).get();
    }

    @Override
    public Analyse_User addAnalyseUser(Analyse_User analyse_user) {
        return analyse_userRepository.save(analyse_user);
    }

    @Override
    public void deleteAnalyseUserAll() {
        analyse_userRepository.deleteAll();
    }
}
