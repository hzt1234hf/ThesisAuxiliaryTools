package com.paper.auxiliary.repository.User;

import com.paper.auxiliary.entity.user.Analyse_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Analyse_UserRepository extends JpaRepository<Analyse_User, Integer> {
//    @Query(value = "SELECT * FROM analyse_user AS au WHERE au.user_date > :date ORDER BY au.user_date LIMIT :count",nativeQuery = true)
//    List<Analyse_User> findFromDate(@Param("date") String date, @Param("count") Integer count);

    @Query(value = "SELECT au.open_id, au.wx_account,SUM(au.value) AS count FROM analyse_user AS au " +
            "WHERE DATE_FORMAT(au.user_date,'%Y-%m-%d') >= :date GROUP BY au.open_id ORDER BY count DESC",nativeQuery = true)
    List<Object[]> findFromDate(@Param("date") String date);
}
