package io.wolverine.common.job;

public class TaskId {
	private String taskId;
	private String jobId;
	private String containerId;
	private String processId;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
		return "TaskId [taskId=" + taskId + ", jobId=" + jobId + ", containerId=" + containerId + ", processId="
				+ processId + "]";
	}
	public TaskId(String taskId, String jobId, String containerId, String processId) {
		super();
		this.taskId = taskId;
		this.jobId = jobId;
		this.containerId = containerId;
		this.processId = processId;
	}
	public TaskId() {
		super();
		// TODO Auto-generated constructor stub
	}
}
