package io.wolverine.common.task;

public interface WolverineTaskManager {
	public void launchTask(TaskInfo task); 
	public void killTask(String taskId) ;
	public void killAllTasks();
	
	public void frameworkMsg(byte[] data) ;
	public void sendFrameworkMsg(byte[] data);
	
	public void sendStatusUpdate(byte[] data);
}
