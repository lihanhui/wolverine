package io.wolverine.common.job;

public interface WolverineJobContext {
	void sendFrameworkMessage(String taskId, byte[] data);
	void killTask(String taskId);
	void launchTasks(String jobId, TaskSpec taskSpec);
}
