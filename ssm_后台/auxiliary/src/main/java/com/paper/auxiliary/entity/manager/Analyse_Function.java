package com.paper.auxiliary.entity.manager;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name="analyse_function")
public class Analyse_Function {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FuncId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '功能模块统计信息ID'")
    private Integer funcId;

//    @OneToOne(targetEntity = Module.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "ModuleId", referencedColumnName = "module_id", unique = false, nullable = false)
//    private Module module;
    @Column(name = "ModuleId", unique = false, nullable = false, columnDefinition = "SMALLINT COMMENT '功能模块ID'")
    private Integer moduleId;

    @Column(name = "value", unique = false, nullable = false, columnDefinition = "INT(32) COMMENT '数值'")
    private BigInteger value;

    //@Temporal(TemporalType.TIME)
    @Column(name = "FuncDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '功能模块统计信息添加时间'")
    private Date funcDate;

    public Analyse_Function() {
    }

    public Analyse_Function(Integer moduleId, BigInteger value) {
        this.moduleId = moduleId;
        this.value = value;
    }

    public Analyse_Function(Integer moduleId, BigInteger value, Date funcDate) {
        this.moduleId = moduleId;
        this.value = value;
        this.funcDate = funcDate;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public Date getFuncDate() {
        return funcDate;
    }

    public void setFuncDate(Date funcDate) {
        this.funcDate = funcDate;
    }
}
