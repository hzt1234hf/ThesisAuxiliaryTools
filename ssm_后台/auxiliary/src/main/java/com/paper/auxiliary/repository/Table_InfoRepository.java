package com.paper.auxiliary.repository;

import com.paper.auxiliary.entity.Table_Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Table_InfoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<String,List<Table_Info>> tableInfoMap;

    public Table_InfoRepository() {
        tableInfoMap = new HashMap<String,List<Table_Info>>();
    }

    @SuppressWarnings("unchecked")
    public List<Table_Info> getTableColumnInfo(String tableName)
    {
        if(tableInfoMap.containsKey(tableName))
        {
            return tableInfoMap.get(tableName);
        }else{
            List<Table_Info> tmp = jdbcTemplate.query("SELECT col.COLUMN_NAME,col.COLUMN_COMMENT,col.IS_NULLABLE" +
                    " FROM information_schema.COLUMNS AS col" + //注意行开头的空格
                    " WHERE col.TABLE_NAME='"+ tableName +"'" +
//                    " AND col.TABLE_SCHEMA='auxiliary'" +
                    " AND col.COLUMN_KEY != 'PRI'" +
                    " AND (col.COLUMN_DEFAULT IS NULL OR col.COLUMN_DEFAULT != 'current_timestamp()')" +
                    "", new BeanPropertyRowMapper(Table_Info.class));

            tableInfoMap.put(tableName,tmp);
            return tmp;
        }
    }

    public void resetTableInfoMap()
    {
        tableInfoMap.clear();
    }
}
