package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.model.Vo.TaskInformationsVo;
import com.tobeyond.blog.service.IQuartzService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    SchedulerFactoryBean schedulerBean;

    @Override
    public void initScheduler() {

    }

    @Override
    public String resumeScheduler(String key) {
        return null;
    }

    @Override
    public String resumeSchedulerAll() {
        return null;
    }

    @Override
    public String addScheduler(String key) {
        return null;
    }

    @Override
    public void updateTaskInformations(String taskNo) {

    }

    @Override
    public void saveTaskErrors(String taskRecordsId, String key, String values) {

    }

    @Override
    public String delScheduler(String key) {
        return null;
    }

    @Override
    public List<TaskInformationsVo> getList(int currentPage) {
        Scheduler scheduler = schedulerBean.getScheduler();
        try {
            List<String> jobGroupNames = scheduler.getJobGroupNames();
            for(String name : jobGroupNames){
                System.out.println(name);

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public String executeOnce(String taskNo) {
        return null;
    }
}
