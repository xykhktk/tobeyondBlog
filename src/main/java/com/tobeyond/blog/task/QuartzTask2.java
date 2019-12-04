package com.tobeyond.blog.task;

import com.tobeyond.blog.util.DateKit;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzTask2 extends QuartzJobBean{

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("QuartzTask2 " + DateKit.getStringNowTime());
    }

}
