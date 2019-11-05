package com.paper.auxiliary.entity.manager;

import javax.persistence.*;

//@Entity
public class Management_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "INT(8) COMMENT 'id'")
    private Integer id;

    @Column(name = "type", unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT '类别'")
    private String type;

    @Column(name = "name", unique = false, nullable = false, columnDefinition = "VARCHAR(32) COMMENT '名称'")
    private String name;

    @Column(name = "parent_id", unique = false, nullable = false, columnDefinition = "INT(8) COMMENT '父类id'")
    private Integer parentId;

}
