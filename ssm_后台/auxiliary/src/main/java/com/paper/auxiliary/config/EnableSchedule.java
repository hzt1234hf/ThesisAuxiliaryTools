package com.paper.auxiliary.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 该类用于开启多线程功能
 */
@Configuration
@ComponentScan("com.paper.auxiliary.task")
@EnableScheduling
public class EnableSchedule {
}
