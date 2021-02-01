package io.wolverine.common.job;

public class AbstractWolverineJobContext implements WolverineJobContext{
	private WolverineJobManager jobManager;
	@Override
	public void sendFrameworkMessage(String taskId, byte[] data) {
		this.jobManager.sendFrameworkMessage(taskId, data);
	}

	@Override
	public void killTask(String taskId) {
		jobManager.killTask(taskId);
	}

	@Override
	public void launchTasks(String jobId, TaskSpec taskSpec) {
		this.jobManager.launchTasks(jobId, taskSpec);
	}

}
