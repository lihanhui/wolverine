package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.TaskInfo;

public class AbstractWolverineJobContext implements WolverineJobContext{
	private WolverineJobManager jobManager;
	@Override
	public void sendFrameworkMessage(String taskId, byte[] data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killTask(String taskId) {
		jobManager.killTask(taskId);
	}

	@Override
	public void launchTasks(String jobId, TaskSpec taskSpec) {
		List<Offer> offers = jobManager.queryOffers(taskSpec);
		List<TaskInfo> taskInfos = new ArrayList<>();
	}

}
