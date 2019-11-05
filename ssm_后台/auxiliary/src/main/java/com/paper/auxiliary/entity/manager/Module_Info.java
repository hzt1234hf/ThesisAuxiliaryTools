package com.paper.auxiliary.entity.manager;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="module_info")
public class Module_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ModuleId", unique = true, nullable = false, columnDefinition = "SMALLINT COMMENT '模块模块ID'")
    private Integer moduleId;

    @Column(name = "Name", unique = false, nullable = false, columnDefinition = "VARCHAR(128) COMMENT '功能模块名称 '")
    private String name;

    @Column(name = "ExecutionPath", unique = false, nullable = false, columnDefinition = "VARCHAR(512) COMMENT '功能模块路径 '")
    private String executionPath;

    @Column(name = "Introduction", unique = false, nullable = false, columnDefinition = "TEXT COMMENT '功能模块介绍 '")
    private String introduction;

    @Column(name = "AddDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '功能模块添加时间 '")
    private Date addDate;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionPath() {
        return executionPath;
    }

    public void setExecutionPath(String executionPath) {
        this.executionPath = executionPath;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "Module_Info{" +
                "moduleId=" + moduleId +
                ", name='" + name + '\'' +
                ", executionPath='" + executionPath + '\'' +
                ", introduction='" + introduction + '\'' +
                ", addDate=" + addDate +
                '}';
    }
}
