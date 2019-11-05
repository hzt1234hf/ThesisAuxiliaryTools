package com.paper.auxiliary.entity.user;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_log")
public class User_Log {
    @Id
//    @SequenceGenerator(name="seq_userinfo_generator", sequenceName="seq_userinfo", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_userinfo_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '日志ID'")
    private Integer logId;

//    @OneToOne(targetEntity = User_Info.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "open_id",  referencedColumnName = "open_id", unique = false, nullable = false)
//    private User_Info user_Info;

    @Column(name = "OpenId", unique = false, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '用户OpenID'")
    private String openId;

//    @OneToOne(targetEntity = Module.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "module_id", referencedColumnName = "module_id", unique = false, nullable = false)
//    private Module module;

    @Column(name = "ModuleId", unique = false, nullable = false, columnDefinition = "SMALLINT COMMENT '模块ID'")
    private Integer moduleId;

    @Column(name = "IsFinished", unique = false, nullable = true, columnDefinition = "BOOLEAN COMMENT '是否处理完成'")
    private Boolean isFinished;

//    @Column(name = "LogDate", unique = false, nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LogDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '日志插入时间'")
    private Date logDate;

    public User_Log() {
    }

    public User_Log(String openId, Integer moduleId, Boolean isFinished) {
        this.openId = openId;
        this.moduleId = moduleId;
        this.isFinished = isFinished;
    }

    public User_Log(Integer logId, Integer moduleId, Boolean isFinished, Date logDate) {
        this.logId = logId;
        this.moduleId = moduleId;
        this.isFinished = isFinished;
        this.logDate = logDate;
    }

    public User_Log( Integer logId, String openId, Boolean isFinished, Date logDate) {
        this.logId = logId;
        this.openId = openId;
        this.isFinished = isFinished;
        this.logDate = logDate;
    }

    public User_Log(String openId, Boolean isFinished, Date logDate) {
        this.openId = openId;
        this.isFinished = isFinished;
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "User_Log{" +
                "logId=" + logId +
                ", openId='" + openId + '\'' +
                ", moduleId=" + moduleId +
                ", isFinished=" + isFinished +
                ", logDate=" + logDate +
                '}';
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }
}
