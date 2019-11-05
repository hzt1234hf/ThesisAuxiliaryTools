package com.paper.auxiliary.entity.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_output")
public class User_Output implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OutId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '输出数据ID'")
    private Integer outId;

//    @OneToOne(targetEntity = User_Log.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "log_id", referencedColumnName = "log_id", unique = false, nullable = false)
//    private Integer logId;
    @Column(name = "LogId", unique = false, nullable = false, columnDefinition = "INT(32) COMMENT '日志ID'")
    private Integer logId;

    @Column(name = "OutData", unique = false, nullable = false, columnDefinition = "LONGTEXT COMMENT '输出数据'")
    private String outData;

//    @Column(name = "Date", unique = false, nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '数据插入时间'")
    private Date date;

    public User_Output() {
    }

    public User_Output(Integer logId, String outData) {
        this.logId = logId;
        this.outData = outData;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getOutData() {
        return outData;
    }

    public void setOutData(String outData) {
        this.outData = outData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
