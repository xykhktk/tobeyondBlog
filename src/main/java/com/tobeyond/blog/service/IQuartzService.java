package com.tobeyond.blog.service;

import com.tobeyond.blog.model.Vo.TaskInformationsVo;

import java.util.List;

public interface IQuartzService {

	public void initScheduler();

	public String resumeScheduler(String key);

	public String resumeSchedulerAll();

	public String addScheduler(String key);

//	public TaskRecordsEntity beforeExecute(String taskNo);

	public void updateTaskInformations(String taskNo);

//	public TaskRecordsEntity modifyTaskRecord(int failCount, Long taskRecordsId);

	public void saveTaskErrors(String taskRecordsId, String key, String values);
	
	public String delScheduler(String key);
	
	public List<TaskInformationsVo> getList(int currentPage);

	public int getTotalCount();
	
//	public TaskInformationsDetailVo getTaskDetail(String taskNo);
	
//	public int editTaskInformation(TaskInformationsEntity entity);
	
//	public TaskInformationsEntity selectTaskInfoById(Long id);
	
	public String executeOnce(String taskNo);
}
