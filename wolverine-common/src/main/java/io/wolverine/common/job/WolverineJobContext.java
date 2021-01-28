package io.wolverine.common.job;

import java.util.Collection;

import org.apache.mesos.Protos.Filters;

import io.wolverine.common.task.Request;
import io.wolverine.common.task.TaskInfo;

public interface WolverineJobContext {
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
