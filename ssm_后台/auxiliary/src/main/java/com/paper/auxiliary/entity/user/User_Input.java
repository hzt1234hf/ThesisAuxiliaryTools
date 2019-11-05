package com.paper.auxiliary.entity.user;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_input")
public class User_Input implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '输入数据ID'")
    private Integer inId;

//    @OneToOne(targetEntity = User_Log.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "log_id", referencedColumnName = "log_id", unique = false, nullable = false)
//    private User_Log user_Log;
    @Column(name = "LogId", unique = false, nullable = false, columnDefinition = "INT(32) COMMENT '日志ID'")
    private Integer logId;

    @Column(name = "InData", unique = false, nullable = false, columnDefinition = "LONGTEXT COMMENT '输入数据'")
    private String inData;

//    @Column(name = "Date", unique = false, nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '数据插入时间'")
    private Date date;

    public User_Input() {
    }

    public User_Input(Integer logId, String inData) {
        this.logId = logId;
        this.inData = inData;
    }

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getInData() {
        return inData;
    }

    public void setInData(String inData) {
        this.inData = inData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
