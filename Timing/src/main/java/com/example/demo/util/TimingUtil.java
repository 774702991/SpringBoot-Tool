package com.example.demo.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author FSARSIGHT-IOT-01
 * @date Created in 2019-12-05 11:01
 */
//@Component
public class TimingUtil {

//    @Scheduled(fixedDelay = 5000)
    public void test(){
        System.out.println("5秒后执行");
    }

    public static void main(String[] args) {
        System.out.println(Test.TEST);
    }
}
enum Test{
    TEST(),
}
