package io.wolverine.common.message;

public class TaskStatus {
	private org.apache.mesos.Protos.TaskStatus taskStatus;

	public TaskStatus(org.apache.mesos.Protos.TaskStatus taskStatus) {
		super();
		this.taskStatus = taskStatus;
	}
	
}
