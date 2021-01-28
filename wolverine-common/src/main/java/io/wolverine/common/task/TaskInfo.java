package io.wolverine.common.task;

public class TaskInfo {
	private org.apache.mesos.Protos.TaskInfo taskInfo;

	public TaskInfo(org.apache.mesos.Protos.TaskInfo taskInfo) {
		super();
		this.taskInfo = taskInfo;
	}
	
}
