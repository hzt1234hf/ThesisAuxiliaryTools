package com.paper.auxiliary.entity.manager;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="analyse_performance")
public class Analyse_Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PerfId", unique = true, nullable = false, columnDefinition = "INT(32) COMMENT '性能统计信息ID'")
    private Integer perfId;

    @Column(name = "PerfCpu", unique = false, nullable = false, columnDefinition = "INT(8) COMMENT 'CPU性能数值'")
    private Integer perfCpu;

    @Column(name = "PerfGpu", unique = false, nullable = false, columnDefinition = "INT(8) COMMENT 'GPU性能数值'")
    private Integer perfGpu;

    @Column(name = "PerfMem", unique = false, nullable = false, columnDefinition = "INT(8) COMMENT '内存性能数值'")
    private Integer perfMem;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PerfDate", unique = false, nullable = false, columnDefinition = "TIMESTAMP COMMENT '性能统计信息时间'")
    private Date perfDate;

    public Analyse_Performance() {
    }

    public Analyse_Performance(Integer perfCpu, Integer perfGpu, Integer perfMem) {
        this.perfCpu = perfCpu;
        this.perfGpu = perfGpu;
        this.perfMem = perfMem;
    }

    @Override
    public String toString() {
        return "Analyse_Performance{" +
                "perfId=" + perfId +
                ", perfCpu=" + perfCpu +
                ", perfGpu=" + perfGpu +
                ", perfMem=" + perfMem +
                ", perfDate=" + perfDate +
                '}';
    }

    public Integer getPerfId() {
        return perfId;
    }

    public void setPerfId(Integer perfId) {
        this.perfId = perfId;
    }

    public Integer getPerfCpu() {
        return perfCpu;
    }

    public void setPerfCpu(Integer perfCpu) {
        this.perfCpu = perfCpu;
    }

    public Integer getPerfGpu() {
        return perfGpu;
    }

    public void setPerfGpu(Integer perfGpu) {
        this.perfGpu = perfGpu;
    }

    public Integer getPerfMem() {
        return perfMem;
    }

    public void setPerfMem(Integer perfMem) {
        this.perfMem = perfMem;
    }

    public Date getPerfDate() {
        return perfDate;
    }

    public void setPerfDate(Date perfDate) {
        this.perfDate = perfDate;
    }
}
