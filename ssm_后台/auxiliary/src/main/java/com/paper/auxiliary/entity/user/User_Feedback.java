package com.paper.auxiliary.entity.user;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User_Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackInfoId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '反馈信息ID'")
    private Integer feedbackInfoId;

    @Column(name = "OpenId", unique = false, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '用户OpenID'")
    private String openId;

    @Column(name = "Content", unique = false, nullable = false, columnDefinition = "TEXT COMMENT '反馈内容 '")
    private String content;

    @Column(name = "Contact", unique = false, nullable = true, columnDefinition = "TEXT COMMENT '联系方式 '")
    private String contact;

    @Column(name = "Date", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '用户上次登录时间'")
    private Date date;

    public User_Feedback() {
    }

    public User_Feedback(String openId, String content, String contact) {
        this.openId = openId;
        this.content = content;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Feedback_Info{" +
                "feedbackInfoId=" + feedbackInfoId +
                ", openId='" + openId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getFeedbackInfoId() {
        return feedbackInfoId;
    }

    public void setFeedbackInfoId(Integer feedbackInfoId) {
        this.feedbackInfoId = feedbackInfoId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
