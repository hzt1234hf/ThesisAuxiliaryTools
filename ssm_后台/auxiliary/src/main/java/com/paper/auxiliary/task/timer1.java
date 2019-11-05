package com.paper.auxiliary.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class timer1 {
    //@Scheduled(fixedDelay=5000)
    public void print()
    {
        System.out.println("Hello");
    }
}
