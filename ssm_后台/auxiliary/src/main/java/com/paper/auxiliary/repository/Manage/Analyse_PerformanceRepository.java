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
public interface Analyse_PerformanceRepository extends JpaRepository<Analyse_Performance, Integer> {

    @Query(value = "SELECT * FROM analyse_performance af WHERE af.perf_date >= :start AND af.perf_date <= :end",nativeQuery = true)
    List<Analyse_Performance> getAnalysePerformanceBetweenDate(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "SELECT * FROM analyse_performance AS ap WHERE ap.perf_date > :date ORDER BY ap.perf_date LIMIT :count",nativeQuery = true)
    List<Analyse_Performance> findFromDate(@Param("date") String date,@Param("count") Integer count);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Analyse_Performance af WHERE af.perfDate < :date")
    void deleteBeforeDate(@Param("date") Date date);


}
