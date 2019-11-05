package com.paper.auxiliary;

import com.paper.auxiliary.entity.Table_Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @SuppressWarnings("unchecked")
    public void test1()
    {
        List<Table_Info> ary = (List<Table_Info>)jdbcTemplate.query("SELECT column_name,column_comment " +
                "FROM information_schema.COLUMNS " +
                "where TABLE_NAME='module_info' AND TABLE_SCHEMA='auxiliary'", new BeanPropertyRowMapper(Table_Info.class));
        System.out.println(ary);

        System.out.println(ary.size());
    }
}
