package com.paper.auxiliary.entity.user;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="analyse_user")
public class Analyse_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '用户分析统计信息ID'")
    private Integer userId;

    @Column(name = "OpenId", unique = false, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '用户微信OpenID'")
    private String openId;

    @Column(name = "wxAccount", unique = false, nullable = true, columnDefinition = "VARCHAR(64) DEFAULT 'null' COMMENT '用户微信账号'")
    private String wxAccount;

    @Column(name = "value", unique = false, nullable = false, columnDefinition = "INT(32) DEFAULT 0 COMMENT '数值'")
    private BigInteger value;

    //@Temporal(TemporalType.TIME)
    @Column(name = "UserDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '功能模块统计信息添加时间'")
    private Date userDate;

    public Analyse_User() {
    }

    public Analyse_User(String openId, String wxAccount, BigInteger value) {
        this.openId = openId;
        this.wxAccount = wxAccount;
        this.value = value;
    }

    public Analyse_User(String openId, String wxAccount, BigInteger value, Date userDate) {
        this.openId = openId;
        this.wxAccount = wxAccount;
        this.value = value;
        this.userDate = userDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }
}
