package com.paper.auxiliary.repository.Manage;

import com.paper.auxiliary.entity.manager.Analyse_Function;
import com.paper.auxiliary.entity.manager.Analyse_Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface Analyse_FunctionRepository extends JpaRepository<Analyse_Function, Integer> {
    @Query(value = "SELECT * FROM analyse_function af WHERE af.func_date >= :start AND af.func_date <= :end",nativeQuery = true)
    List<Analyse_Function> getAnalyseFunctionBetweenDate(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "SELECT * FROM analyse_function AS ap WHERE DATE_FORMAT(ap.func_date,'%Y-%m-%d') >= DATE_FORMAT(:date,'%Y-%m-%d') ORDER BY ap.func_date LIMIT :count",nativeQuery = true)
    List<Analyse_Function> findFromDate(@Param("date") String date, @Param("count") Integer count);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Analyse_Function af WHERE af.moduleId=:mid")
    void deleteByMoudleId(@Param("mid") Integer mid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Analyse_Function af WHERE af.funcDate < :date")
    void deleteBeforeDate(@Param("date") Date date);
}
