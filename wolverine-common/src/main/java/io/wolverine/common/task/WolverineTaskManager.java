package io.wolverine.common.task;

import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskState;

public interface WolverineTaskManager {
	public void launchTask(TaskInfo task); 
	public void killTask(String taskId) ;
	public void killAllTasks();
	
	public void onFrameworkMsg(byte[] data) ;
	public void sendFrameworkMsg(byte[] data);
	
	public void sendStatusUpdate(TaskID taskId, TaskState taskState, byte[] data);
}
