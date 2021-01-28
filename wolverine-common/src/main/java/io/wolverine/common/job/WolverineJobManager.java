package io.wolverine.common.job;

import java.util.Collection;
import java.util.List;

import org.apache.mesos.Protos.Filters;

import io.wolverine.common.task.Offer;
import io.wolverine.common.task.Request;
import io.wolverine.common.task.TaskInfo;
import io.wolverine.common.task.TaskStatus;

public interface WolverineJobManager {
	
	public void resourceOffers(List<Offer> offers) ;
	public void offerRescinded(String offerId);
	public void statusUpdate(TaskStatus status);
	
	void sendFrameworkMessage(String executorId,
            String slaveId,
            byte[] data);
	
	void requestResources(Collection<Request> requests);
	void declineOffer(String offerId);
	void acceptOffers(Collection<String> offerIds,
            Collection<org.apache.mesos.Protos.Offer.Operation> operations,
            Filters filters);
	void killTask(String taskId);
	void launchTasks(Collection<String> offerIds, Collection<TaskInfo> tasks);
}
