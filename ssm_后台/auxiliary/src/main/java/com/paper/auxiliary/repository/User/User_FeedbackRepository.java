package com.paper.auxiliary.repository.User;

import com.paper.auxiliary.entity.manager.Notification;
import com.paper.auxiliary.entity.user.User_Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface User_FeedbackRepository extends JpaRepository<User_Feedback,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User_Feedback uf WHERE uf.openId = : openid")
    void deleteByOpenid(Integer openid);


    @Query(value = "SELECT COUNT(uf) FROM User_Feedback uf")
    Integer getUserFeedbackCount();

    @Query(value = "SELECT * FROM user_feedback LIMIT :index_1,:index_2",nativeQuery = true)
    List<User_Feedback> getUserFeedbackScope(Integer index_1, Integer index_2);


    @Modifying
    @Transactional
    @Query("DELETE FROM User_Feedback uf WHERE uf.feedbackInfoId IN (:fids)")
        //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int deleteUserFeedbackByFids(List<Integer> fids);
}
