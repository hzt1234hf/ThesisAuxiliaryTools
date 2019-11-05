package com.paper.auxiliary.entity.manager;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**CREATE TABLE admin_info(
 admin_id INT(8) PRIMARY KEY,
 admin_name VARCHAR(32) NOT NULL,
 position VARCHAR(32) NOT NULL DEFAULT '',
 department VARCHAR(32) NOT NULL DEFAULT '',
 last_login_date TIMESTAMP NOT NULL DEFAULT 0,
 permis_user BOOLEAN NOT NULL DEFAULT 0,
 permis_setting BOOLEAN NOT NULL DEFAULT 0,
 permis_analyse BOOLEAN NOT NULL DEFAULT 0,
 permis_content BOOLEAN NOT NULL DEFAULT 0
 )ENGINE=INNODB DEFAULT CHARSET=utf8;
 *
 * */

@Entity
@Table(name="admin_info")
public class Admin_Info {
    @Id
    @Column(name = "AdminAccount", unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT '管理员账号'")
    private String adminAccount;

    @Column(name = "AdminPassword", unique = false, nullable = false, columnDefinition = "VARCHAR(60) COMMENT '管理员密码'")
    private String adminPassword;

    @Column(name = "AdminName", unique = false, nullable = false, columnDefinition = "VARCHAR(32) COMMENT '管理员姓名'")
    private String adminName;

    @Column(name = "position", unique = false, nullable = true, columnDefinition = "VARCHAR(32) DEFAULT '' COMMENT '管理员职位'")
    private String position;

    @Column(name = "department", unique = false, nullable = true, columnDefinition = "VARCHAR(32) DEFAULT '' COMMENT '管理员所属部门'")
    private String department;

    @Column(name ="LoginCount", unique = false, nullable = false, columnDefinition = "INT(32) DEFAULT 0 COMMENT '登录次数'")
    private Integer loginCount;

    @Column(name = "LastLoginDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '上次登录时间'")
    private Date lastLoginDate;

    @Column(name = "PermisContent", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '内容修改查看权限'")
    private Boolean permisContent;

    @Column(name = "PermisAnalyse", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '统计分析查看权限'")
    private Boolean permisAnalyse;

    @Column(name = "PermisUser", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '用户设置修改权限'")
    private Boolean permisUser;

    @Column(name = "PermisSetting", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '设置查看修改权限'")
    private Boolean permisSetting;

    @Column(name = "SuperAdmin", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '设置超级管理员'")
    private Boolean superAdmin;

    @Transient
    private String newPassword;

    public Admin_Info() {
    }

    public Admin_Info(String adminAccount,String adminName, String department, String position, Integer loginCount, Date lastLoginDate, Boolean permisContent, Boolean permisAnalyse, Boolean permisUser, Boolean permisSetting, Boolean superAdmin) {
        this.adminAccount = adminAccount;
        this.adminName = adminName;
        this.position = position;
        this.department = department;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.permisUser = permisUser;
        this.permisSetting = permisSetting;
        this.permisAnalyse = permisAnalyse;
        this.permisContent = permisContent;
        this.superAdmin = superAdmin;
    }


    @Override
    public String toString() {
        return "Admin_Info{" +
                "adminAccount='" + adminAccount + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminName='" + adminName + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", loginCount=" + loginCount +
                ", lastLoginDate=" + lastLoginDate +
                ", permisContent=" + permisContent +
                ", permisAnalyse=" + permisAnalyse +
                ", permisUser=" + permisUser +
                ", permisSetting=" + permisSetting +
                ", superAdmin=" + superAdmin +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Boolean getPermisUser() {
        return permisUser;
    }

    public void setPermisUser(Boolean permisUser) {
        this.permisUser = permisUser;
    }

    public Boolean getPermisSetting() {
        return permisSetting;
    }

    public void setPermisSetting(Boolean permisSetting) {
        this.permisSetting = permisSetting;
    }

    public Boolean getPermisAnalyse() {
        return permisAnalyse;
    }

    public void setPermisAnalyse(Boolean permisAnalyse) {
        this.permisAnalyse = permisAnalyse;
    }

    public Boolean getPermisContent() {
        return permisContent;
    }

    public void setPermisContent(Boolean permisContent) {
        this.permisContent = permisContent;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
