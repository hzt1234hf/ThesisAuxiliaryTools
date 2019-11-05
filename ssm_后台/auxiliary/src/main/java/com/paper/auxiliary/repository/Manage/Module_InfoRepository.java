package com.paper.auxiliary.repository.Manage;

import com.paper.auxiliary.entity.manager.Module_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Module_InfoRepository extends JpaRepository<Module_Info, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Module_Info mi WHERE mi.moduleId IN (:ids)")
    int deleteByIds(@Param("ids") List<Integer> ids);
}
