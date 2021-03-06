package com.tobeyond.blog.model.bo;


public class TaskInformationsBo {

	private String jobName;
	private String groupName;
	private String nextFireTime;
	private String triggerState;

	public String getTriggerStateCN() {
		return triggerStateCN;
	}

	public void setTriggerStateCN(String triggerStateCN) {
		this.triggerStateCN = triggerStateCN;
	}

	private String triggerStateCN;
	private String cronExpression;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
}
