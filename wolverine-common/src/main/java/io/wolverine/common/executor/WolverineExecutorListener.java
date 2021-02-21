package io.wolverine.common.executor;

import org.apache.mesos.Protos.TaskInfo;

public interface WolverineExecutorListener {
	public void launchTask(TaskInfo task); 
	public void killTask(String taskId) ;
	public void killAllTasks();
	public void onFrameworkMsg(byte[] data) ;
}
