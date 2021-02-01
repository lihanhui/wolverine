package io.wolverine.common.job;

import java.util.List;

import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.TaskStatus;

public interface WolverineJobManager {
	
	void resourceOffers(List<Offer> offers) ;
	void offerRescinded(String offerId);
	void statusUpdate(TaskStatus status);
	void frameworkMessage(byte[] data);
	
	void sendFrameworkMessage(String taskId,
            byte[] data);
	
	void killTask(String taskId);
	void launchTasks(String jobId, TaskSpec taskSpec);
	
}
