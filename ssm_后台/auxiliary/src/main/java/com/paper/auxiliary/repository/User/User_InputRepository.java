package com.paper.auxiliary.repository.User;

import com.paper.auxiliary.entity.user.User_Input;
import com.paper.auxiliary.entity.user.User_Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface User_InputRepository extends JpaRepository<User_Input, Integer> {

    @Query(value = "SELECT ui FROM User_Input ui WHERE ui.logId=:lid")
    List<User_Input> findByLogId(@Param("lid") Integer lid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User_Input ui WHERE ui.logId=:lid")
    void deleteByLogId(@Param("lid") Integer lid);
}
