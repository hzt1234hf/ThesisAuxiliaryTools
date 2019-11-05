package com.paper.auxiliary.repository.Manage;

import com.paper.auxiliary.entity.manager.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query(value = "SELECT COUNT(nt) FROM Notification AS nt WHERE nt.isScrolling = false")
    Integer getNotificationCount();

    @Query(value = "SELECT * FROM notification LIMIT :index_1,:index_2",nativeQuery = true)
    List<Notification> getNotificationScope(Integer index_1, Integer index_2);

    @Modifying
    @Transactional
    @Query("UPDATE Notification nt SET nt.isScrolling = :val WHERE nt.noticeId = :nid")
    int changeNotificationScrolling(Integer nid, Boolean val);


    @Modifying
    @Transactional
    @Query("DELETE FROM Notification nt WHERE nt.noticeId IN (:nids)")
        //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int deleteNotificationsById(List<Integer> nids);
}
