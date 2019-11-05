package com.paper.auxiliary.repository.User;

import com.paper.auxiliary.entity.user.User_Info;
import com.paper.auxiliary.entity.user.User_Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface User_InfoRepository extends JpaRepository<User_Info, String> {
    @Query(value = "SELECT COUNT(ui) FROM User_Info ui")
    Integer getUserInfoCount();

    @Query(value = "SELECT * FROM user_info LIMIT :index_1,:index_2",nativeQuery = true)
    List<User_Info> getUserInfoScope(Integer index_1, Integer index_2);

    @Modifying
    @Transactional
    @Query("UPDATE User_Info ui SET ui.lastUseDate = null WHERE ui.openId=:oid")
    Integer updateUserInfoDate(@Param("oid") String oid);

}
