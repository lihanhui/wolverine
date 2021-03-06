package io.wolverine.message.task;

public class TaskIdMsg {
	private String taskId;
	private String jobId;
	private String containerId;
	private String processId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	@Override
	public String toString() {
		return "TaskIdMsg [taskId=" + taskId + ", jobId=" + jobId + ", containerId=" + containerId + ", processId="
				+ processId + "]";
	}
	public TaskIdMsg() {
		super();
	}
}
