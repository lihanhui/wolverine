package io.wolverine.common.task;

public class TaskInfo {
	private org.apache.mesos.Protos.TaskInfo taskInfo;

	public TaskInfo(org.apache.mesos.Protos.TaskInfo taskInfo) {
		super();
		this.taskInfo = taskInfo;
	}
	public org.apache.mesos.Protos.TaskInfo getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(org.apache.mesos.Protos.TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	
}
