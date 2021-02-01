package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.Filters;
import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.Offer.Operation;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.Resource;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskStatus;
import org.apache.mesos.SchedulerDriver;

public abstract class AbstractWolverineJobManager implements WolverineJobManager{
	private SchedulerDriver schedulerDriver;
	private ConcurrentHashMap<String, Offer> offerMap = new ConcurrentHashMap<>();
	
	public AbstractWolverineJobManager(SchedulerDriver schedulerDriver) {
		super();
		this.schedulerDriver = schedulerDriver;
	}

	@Override
	public void resourceOffers(List<Offer> offers) {
		for(Offer o: offers){
			offerMap.put(o.getId().getValue(), o);
		}
	}

	@Override
	public void offerRescinded(String offerId) {
		offerMap.remove(offerId);
	}

	@Override
	public void statusUpdate(TaskStatus status) {
		//status.getTaskStatus().ge
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
			taskInfos2.add(t);
		});
		this.schedulerDriver.launchTasks(offerIds2, taskInfos2);
	}
	@Override
	public List<Offer> queryOffers(TaskSpec taskSpec){
		List<Offer> offers = new ArrayList<>();
		for(Offer o:this.offerMap.values()) {
			boolean ok = true;
			for(Resource r: o.getResourcesList()) {
				if("cpus".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getCores()) {
					ok = false;
					break;
				}
				if("mem".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getMemory()) {
					ok = false;
					break;
				}
				if("disk".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getDisk()) {
					ok = false;
					break;
				}
			}
			if(ok) offers.add(o);
			if(offers.size() >= taskSpec.getTasks()) break;
		}
		return offers;
	}
	public void launchTask(String offerId, TaskInfo taskInfo) {
		OfferID.Builder b = OfferID.newBuilder();
		b.setValue(offerId);
		this.schedulerDriver.launchTasks(Arrays.asList(b.build()), Arrays.asList(taskInfo));
	}
	public TaskInfo getTaskInfo(String taskId) {
		return null;
	}
}
