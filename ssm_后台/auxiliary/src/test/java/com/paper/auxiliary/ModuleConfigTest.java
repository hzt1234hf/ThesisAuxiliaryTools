package com.paper.auxiliary;

import com.paper.auxiliary.config.EnvironmentConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleConfigTest {
    @Autowired
    private EnvironmentConfig environmentConfig;

    @Test
    public void test1()
    {

        System.out.println(environmentConfig.getPyinterpreter());
        System.out.println(environmentConfig.getPyconfigfile());
        System.out.println(environmentConfig.getCorrection());
        System.out.println(environmentConfig.getSimilarity());
        System.out.println(environmentConfig.getTheme());
    }
}
