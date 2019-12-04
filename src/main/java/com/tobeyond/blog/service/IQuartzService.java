package com.tobeyond.blog.service;

import com.tobeyond.blog.model.vo.TaskInformationsVo;
import org.quartz.SchedulerException;

import java.util.List;

public interface IQuartzService {


	public void pauseJob(String jobName,String jobGroup) throws SchedulerException;

	public void resumeJob(String jobName,String jobGroup) throws SchedulerException;

	public String resumeSchedulerAll();

	public void delJob(String jobName,String jobGroup) throws SchedulerException;

	public List<TaskInformationsVo> getList();

}
