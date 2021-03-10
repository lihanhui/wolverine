package io.wolverine.common.task;

import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskState;

public abstract class AbstractWolverineTaskContext implements WolverineTaskContext{
	private WolverineTaskManager taskManager;
	private WolverineTask task;
	
	public AbstractWolverineTaskContext(WolverineTaskManager taskManager, WolverineTask task) {
		super();
		this.taskManager = taskManager;
		this.task = task;
	}
	@Override
	public void sendFrameworkMsg(byte[] data) {
		taskManager.sendFrameworkMsg(data);
	}

	@Override
	public void sendRunningStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_RUNNING, data);
	}
	
	@Override
	public void sendStopStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_KILLING, data);
	}
	
	@Override
	public void sendStartStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_STARTING, data);
	}
	@Override
	public void sendStartedStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_RUNNING, data);
	}

	@Override
	public void sendFinishedStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_FINISHED, data);
	}

	@Override
	public void sendStoppedStatus(byte[] data) {
		TaskID taskId = TaskID.newBuilder().setValue(task.getTaskId()).build();
		taskManager.sendStatusUpdate(taskId, TaskState.TASK_KILLED, data);
	}
}
