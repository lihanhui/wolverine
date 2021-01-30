package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.Filters;
import org.apache.mesos.Protos.Offer.Operation;

import io.wolverine.common.message.Offer;
import io.wolverine.common.message.Request;
import io.wolverine.common.message.TaskInfo;
import io.wolverine.common.message.TaskStatus;

import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.SchedulerDriver;

public abstract class AbstractWolverineJobManager implements WolverineJobManager{
	private SchedulerDriver schedulerDriver;
	
	public AbstractWolverineJobManager(SchedulerDriver schedulerDriver) {
		super();
		this.schedulerDriver = schedulerDriver;
	}

	@Override
	public void resourceOffers(List<Offer> offers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offerRescinded(String offerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statusUpdate(TaskStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frameworkMessage(byte[] data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendFrameworkMessage(String executorId, String slaveId, byte[] data) {
		Protos.ExecutorID.Builder b = Protos.ExecutorID.newBuilder();
		b.setValue(executorId);
		
		Protos.SlaveID.Builder b2 = Protos.SlaveID.newBuilder();
		b2.setValue(slaveId);
		this.schedulerDriver.sendFrameworkMessage(b.build(), b2.build(), data);
	}

	@Override
	public void requestResources(Collection<Request> requests) {
		List<Protos.Request> requests2 = new ArrayList<>();
		requests.forEach( r -> requests2.add(r.getRequest()));
		
		this.schedulerDriver.requestResources(requests2);
	}

	@Override
	public void declineOffer(String offerId) {
		OfferID.Builder b = OfferID.newBuilder();
		b.setValue(offerId);
		this.schedulerDriver.declineOffer(b.build());
	}

	@Override
	public void acceptOffers(Collection<String> offerIds, Collection<Operation> operations, Filters filters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killTask(String taskId) {
		Protos.TaskID.Builder b = Protos.TaskID.newBuilder();
		b.setValue(taskId);
		this.schedulerDriver.killTask(b.build());
	}

	@Override
	public void launchTasks(Collection<String> offerIds, Collection<TaskInfo> tasks) {
		List<OfferID> offerIds2 = new ArrayList<>();
		offerIds.forEach( o -> {
			OfferID.Builder b = OfferID.newBuilder();
			b.setValue(o);
			offerIds2.add(b.build());
		});
		List<Protos.TaskInfo> taskInfos2 = new ArrayList<>();
		tasks.forEach( t -> {
			taskInfos2.add(t.getTaskInfo());
		});
		this.schedulerDriver.launchTasks(offerIds2, taskInfos2);
	}

}
