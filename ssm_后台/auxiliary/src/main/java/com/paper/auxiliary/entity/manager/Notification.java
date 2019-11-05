package com.paper.auxiliary.entity.manager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoticeId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '通知信息ID'")
    private Integer noticeId;

    @Column(name = "Title", unique = false, nullable = false, columnDefinition = "VARCHAR(512) COMMENT '通知信息标题 '")
    private String title;

    @Column(name = "Content", unique = false, nullable = false, columnDefinition = "TEXT COMMENT '通知信息内容 '")
    private String content;

    @Column(name = "IsScrolling", unique = false, nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT '是否是滚动显示内容'")
    private Boolean isScrolling;

    //@Temporal(TemporalType.TIME)
    @Column(name = "NoticeDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '通知信息添加时间'")
    private Date noticeDate;

    public Notification() {
    }

    public Notification(Integer noticeId, String title, String content, Boolean isScrolling) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.isScrolling = isScrolling;
    }
    public Notification(String title, String content, Boolean isScrolling) {
        this.title = title;
        this.content = content;
        this.isScrolling = isScrolling;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsScrolling() {
        return isScrolling;
    }

    public void setIsScrolling(Boolean isScrolling) {
        this.isScrolling = isScrolling;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }
}
