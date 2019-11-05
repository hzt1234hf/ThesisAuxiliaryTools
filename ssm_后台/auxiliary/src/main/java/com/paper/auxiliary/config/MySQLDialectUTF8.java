package com.paper.auxiliary.config;

import org.hibernate.dialect.MySQL8Dialect;

public class MySQLDialectUTF8 extends MySQL8Dialect {
    /**
     * 重写字符编码方式
     * @return 建表时配置语句
     */
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
