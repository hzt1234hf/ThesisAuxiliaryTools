package com.paper.auxiliary.entity.user;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="user_info")
public class User_Info {
    @Id
    @Column(name = "OpenId", unique = true, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '用户微信OpenID'")
    private String openId;

    @Column(name = "wxAccount", unique = false, nullable = true, columnDefinition = "VARCHAR(64) COMMENT '用户微信账号'")
    private String wxAccount;

    //    @Column(name = "LastUseDate", unique = false, nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastUseDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '用户上次登录时间'")
    private Date lastUseDate;

    public User_Info() {

    }

    public User_Info(String openId, String wxAccount) {
        this.openId = openId;
        this.wxAccount = wxAccount;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public Date getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }
}
