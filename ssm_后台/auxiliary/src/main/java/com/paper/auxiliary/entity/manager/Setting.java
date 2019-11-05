package com.paper.auxiliary.entity.manager;

import javax.persistence.*;

@Entity
@Table(name="settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SetId", unique = true, nullable = false, columnDefinition = "SMALLINT COMMENT '设置信息ID'")
    private Integer setId;

    @Column(name = "SetName", unique = true, nullable = false, columnDefinition = "VARCHAR(128) COMMENT '设置名称'")
    private String setName;

    @Column(name = "SetValue", unique = false, nullable = false, columnDefinition = "BOOLEAN COMMENT '设置值'")
    private Boolean setValue;

    @Override
    public String toString() {
        return "Setting{" +
                "setId=" + setId +
                ", setName='" + setName + '\'' +
                ", setValue=" + setValue +
                '}';
    }

    public Setting() {
    }

    public Setting(String setName, Boolean setValue) {
        this.setName = setName;
        this.setValue = setValue;
    }

    public Setting(String setName) {
        this.setName = setName;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Boolean getSetValue() {
        return setValue;
    }

    public void setSetValue(Boolean setValue) {
        this.setValue = setValue;
    }
}
