package io.wolverine.common.message;

import java.util.Arrays;

public class TaskStatusMsg {
	private String jobId;
	private String taskId;
	private byte[] data;
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
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "TaskStatusMsg [jobId=" + jobId + ", taskId=" + taskId + ", data=" + Arrays.toString(data) + "]";
	}
	public TaskStatusMsg(String jobId, String taskId, byte[] data) {
		super();
		this.jobId = jobId;
		this.taskId = taskId;
		this.data = data;
	}
}
