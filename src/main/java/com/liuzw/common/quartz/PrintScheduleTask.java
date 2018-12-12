package com.liuzw.common.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/12 20:33
 */
@Component
public class PrintScheduleTask {

    private final  static Logger logger = LoggerFactory.getLogger(PrintScheduleTask.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void testCron(){
        logger.info("定时任务开始执行，每五秒执行一次----------------->>>>>>>");
    }
}
