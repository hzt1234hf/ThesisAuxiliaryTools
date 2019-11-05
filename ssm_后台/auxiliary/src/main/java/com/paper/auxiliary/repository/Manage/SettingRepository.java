package com.paper.auxiliary.repository.Manage;

import com.paper.auxiliary.entity.manager.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer>
{

    @Modifying
    @Transactional
    @Query("UPDATE Setting se SET se.setValue = ABS(se.setValue-1) WHERE se.setId = :id")
    //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int changeSettingValue(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Setting se SET se.setValue = :val WHERE se.setId = :id")
        //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int setSettingValue(@Param("id") Integer id, @Param("val") Boolean val);

    @Modifying
    @Transactional
    @Query("DELETE FROM Setting se WHERE se.setId IN (:ids)")
        //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int deleteByIds(@Param("ids") List<Integer> ids);
}
