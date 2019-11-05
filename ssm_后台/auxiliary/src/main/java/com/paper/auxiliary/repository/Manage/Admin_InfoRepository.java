package com.paper.auxiliary.repository.Manage;

import com.paper.auxiliary.entity.manager.Admin_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Admin_InfoRepository extends JpaRepository<Admin_Info, String> {
    @Query("SELECT new Admin_Info(ai.adminAccount,ai.adminName,ai.department,ai.position,ai.loginCount,ai.lastLoginDate,ai.permisContent,ai.permisAnalyse,ai.permisUser,ai.permisSetting,ai.superAdmin) " +
            "FROM Admin_Info ai")
    //@Query("SELECT ai FROM Admin_Info ai")
    List<Admin_Info> findAdminInfoAll();

    @Query(value = "SELECT COUNT(ai) FROM Admin_Info ai WHERE ai.adminAccount = :account AND ai.adminPassword = :password")
    Integer hasAdminInfo(@Param("account") String account, @Param("password") String password);

    @Query(value = "SELECT new Admin_Info(ai.adminAccount,ai.adminName,ai.department,ai.position,ai.loginCount,ai.lastLoginDate,ai.permisContent,ai.permisAnalyse,ai.permisUser,ai.permisSetting,ai.superAdmin) " +
            "FROM Admin_Info ai WHERE ai.adminAccount = :account AND ai.adminPassword = :password")
    //@Query("SELECT ai FROM Admin_Info ai WHERE ai.adminAccount = :account AND ai.adminPassword = :password")
    Admin_Info getAdminInfoByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Admin_Info ai SET ai.loginCount = ai.loginCount + 1 WHERE ai.adminAccount = :account")
    int updateAdminInfoLoginInfo(@Param("account") String account);

    @Query(value = "SELECT COUNT(ai) FROM Admin_Info ai WHERE ai.adminAccount=:adminAccount AND ai.adminPassword=:adminPassword")
    Integer getAdminInfoCountByAdminPassword(String adminAccount, String adminPassword);

    @Query(value = "SELECT COUNT(ai) FROM Admin_Info ai WHERE ai.adminAccount=:adminAccount")
    Integer getAdminInfoCountByAdminAccount(String adminAccount);

    @Query(value = "SELECT COUNT(ai) FROM Admin_Info ai")
    Integer getAdminInfoCount();

    @Query(value = "SELECT * FROM admin_info LIMIT :index_1,:index_2",nativeQuery = true)
    List<Admin_Info> getAdminInfoScope(Integer index_1, Integer index_2);


    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.permisContent = :val WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminPermisContent(String adminAccount, Boolean val);

    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.permisAnalyse = :val WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminPermisAnalyse(String adminAccount, Boolean val);

    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.permisUser = :val WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminPermisUser(String adminAccount, Boolean val);

    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.permisSetting = :val WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminPermisSetting(String adminAccount, Boolean val);

    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.adminName = :adminName,ai.department=:department,ai.position=:position WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminInfo(String adminAccount,String adminName,String department,String position);

    @Modifying
    @Transactional
    @Query("UPDATE Admin_Info ai SET ai.adminPassword = :adminPassword WHERE ai.adminAccount = :adminAccount")
    Integer updateAdminPassword(String adminAccount,String adminPassword);

    @Modifying
    @Transactional
    @Query("DELETE FROM Admin_Info ai WHERE ai.adminAccount IN (:accounts)")
        //@Query("UPDATE Setting se SET NOT se.setValue WHERE se.setId = :id")
    int deleteByAccounts(@Param("accounts") List<String> accounts);
}
