package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.model.bo.TaskInformationsBo;
import com.tobeyond.blog.service.IQuartzService;
import com.tobeyond.blog.util.DateKit;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuartzServiceImpl implements IQuartzService {

    //https://www.ktanx.com/blog/p/311

    @Autowired
    SchedulerFactoryBean schedulerBean;

    @Override
    public void pauseJob(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler = schedulerBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.pauseJob(jobKey);
    }

    @Override
    public void resumeJob(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler = schedulerBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.resumeJob(jobKey);
    }

    @Override
    public String resumeSchedulerAll() {
        return null;
    }

    @Override
    public void delJob(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler = schedulerBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.deleteJob(jobKey);
    }

    @Override
    public List<TaskInformationsBo> getList() {
        Scheduler scheduler = schedulerBean.getScheduler();
        List<TaskInformationsBo> taskInformationsBoList = new ArrayList<>();
        try {
            List<String> jobGroupNames = scheduler.getJobGroupNames();
            for(String jobGroupName : jobGroupNames){
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();

                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);//一个job可以有多个trigger
                    for(Trigger trigger : triggers){
                        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
                        String triggerSstateCN = getTriggerStatesCN(scheduler.getTriggerState(triggerKey).name());
                        String nextFireTime = DateKit.dateFormat(trigger.getNextFireTime());

                        System.out.println("[jobName] : " + jobName + ", [groupName] : "
                                + jobGroup + " - "  + triggerSstateCN + " - " + nextFireTime);

                        TaskInformationsBo taskInformationsbBo = new TaskInformationsBo();
                        if (trigger instanceof CronTrigger) {
                            taskInformationsbBo.setCronExpression(((CronTrigger) trigger).getCronExpression());
                        }
                        taskInformationsbBo.setGroupName(jobGroup);
                        taskInformationsbBo.setJobName(jobName);
                        taskInformationsbBo.setNextFireTime(nextFireTime);
                        taskInformationsbBo.setTriggerState(triggerSstateCN);
                        taskInformationsBoList.add(taskInformationsbBo);
                    }
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return taskInformationsBoList;
    }

    private static String getTriggerStatesCN(String key) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("BLOCKED", "阻塞");
        map.put("COMPLETE", "完成");
        map.put("ERROR", "出错");
        map.put("NONE", "不存在");
        map.put("NORMAL", "正常");
        map.put("PAUSED", "暂停");

        map.put("4", "阻塞");
        map.put("2", "完成");
        map.put("3", "出错");
        map.put("-1", "不存在");
        map.put("0", "正常");
        map.put("1", "暂停");
            /*  **STATE_BLOCKED 4 阻塞
        STATE_COMPLETE 2 完成
        STATE_ERROR 3 错误
        STATE_NONE -1 不存在
        STATE_NORMAL 0 正常
        STATE_PAUSED 1 暂停***/
        return map.get(key);
    }

}
