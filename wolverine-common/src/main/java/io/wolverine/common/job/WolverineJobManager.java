package io.wolverine.common.job;

import java.util.Collection;
import java.util.List;

import org.apache.mesos.Protos.Filters;

import io.wolverine.common.message.Offer;
import io.wolverine.common.message.Request;
import io.wolverine.common.message.TaskInfo;
import io.wolverine.common.message.TaskStatus;

public interface WolverineJobManager {
	
	void resourceOffers(List<Offer> offers) ;
	void offerRescinded(String offerId);
	void statusUpdate(TaskStatus status);
	void frameworkMessage(byte[] data);
	
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
