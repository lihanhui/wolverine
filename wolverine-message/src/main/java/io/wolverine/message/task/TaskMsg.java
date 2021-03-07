package io.wolverine.message.task;

public class TaskMsg {
	private String taskId;
	private String jobId;
	private String containerId;
	private String processId;
	
	private Integer status;
	private Integer mode;
	private Long createDate;
	private Long updateDate;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "TaskMsg [taskId=" + taskId + ", jobId=" + jobId + ", containerId=" + containerId + ", processId="
				+ processId + ", status=" + status + ", mode=" + mode + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
	public TaskMsg() {
		super();
	}
}
