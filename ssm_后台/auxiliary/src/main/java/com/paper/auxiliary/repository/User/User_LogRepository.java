package com.paper.auxiliary.repository.User;

import com.paper.auxiliary.entity.user.User_Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface User_LogRepository extends JpaRepository<User_Log, Integer> {

    @Query(value = "SELECT new User_Log (ui.logId, ui.moduleId, ui.isFinished, ui.logDate) FROM User_Log ui WHERE ui.openId=:oid")
    List<User_Log> findByOpenId( @Param("oid") String oid);

//    @Query(value = "SELECT ul.log_id, ul.module_id, ul.is_finished, ul.log_date FROM user_log AS ul WHERE ul.open_id=':oid' ORDER BY ul.log_date DESC LIMIT 20",nativeQuery = true)
//    List<User_Log> findCountByOpenId( @Param("oid") String oid);
    @Query(value = "SELECT new User_Log (ul.logId, ul.moduleId, ul.isFinished, ul.logDate) FROM User_Log AS ul WHERE ul.openId=:oid ORDER BY ul.logDate DESC")
    Page<User_Log> findCountByOpenId(@Param("oid") String oid, Pageable pageable);

    @Query(value = "SELECT new User_Log (ui.logId, ui.openId, ui.isFinished, ui.logDate) FROM User_Log ui WHERE ui.moduleId=:mid")
    List<User_Log> findByModuleId(@Param("mid") Integer mid);

    @Query(value = "SELECT COUNT(*) FROM user_log AS ul WHERE DATE_FORMAT(ul.log_date,'%Y-%m-%d')=:date AND ul.module_id=:mid",nativeQuery = true)
    BigInteger findByModuleIdAndDate(@Param("mid") Integer mid,@Param("date") String date);

    @Query(value = "SELECT COUNT(*) FROM user_log AS ul WHERE DATE_FORMAT(ul.log_date,'%Y-%m-%d')>=:date",nativeQuery = true)
    BigInteger findAllFromDate(@Param("date") String date);

    @Query(value = "SELECT COUNT(*) FROM user_log AS ul WHERE DATE_FORMAT(ul.log_date,'%Y-%m-%d')=:date",nativeQuery = true)
    BigInteger findAllByDate(@Param("date") String date);


    @Query(value = "SELECT ul.open_id, (SELECT ui.wx_account FROM user_info AS ui WHERE ui.open_id = ul.open_id), COUNT(*) AS count FROM user_log AS ul " +
            "WHERE DATE_FORMAT(ul.log_date,'%Y-%m-%d') >= :date GROUP BY ul.open_id ORDER BY count DESC LIMIT 5",nativeQuery = true)
    List<Object[]> findFromDate(@Param("date") String date);

    @Query(value = "SELECT ul.open_id, (SELECT ui.wx_account FROM user_info AS ui WHERE ui.open_id = ul.open_id), COUNT(*) AS count FROM user_log AS ul " +
            "WHERE DATE_FORMAT(ul.log_date,'%Y-%m-%d') = :date GROUP BY ul.open_id ORDER BY count DESC LIMIT 5",nativeQuery = true)
    List<Object[]> findByDate(@Param("date") String date);

    @Query(value = "SELECT * FROM user_log AS ul WHERE ul.is_finished = FALSE LIMIT 1",nativeQuery = true)
    User_Log findOneIsNotFinished();

    @Query(value = "SELECT COUNT(ul) FROM User_Log AS ul WHERE ul.isFinished = FALSE")
    Integer getIsNotFinishedCount();

    @Modifying
    @Transactional
    @Query("UPDATE User_Log ul SET ul.isFinished = TRUE WHERE ul.logId=:lid")
    Integer updateStatusByLogId(@Param("lid") Integer lid);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM User_Log ui WHERE ui.moduleId=:mid AND ui.openId=:oid")
    void deleteByOpenIdAndModuleId(@Param("oid") Integer oid, @Param("mid") Integer mid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User_Log ui WHERE ui.openId=:oid")
    void deleteByOpenId(@Param("oid") String oid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User_Log ui WHERE ui.moduleId=:mid")
    void deleteByModuleId(@Param("mid") Integer mid);
}
