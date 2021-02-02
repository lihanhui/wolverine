package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.Resource;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskStatus;
import org.apache.mesos.SchedulerDriver;

public abstract class AbstractWolverineJobManager implements WolverineJobManager{
	private SchedulerDriver schedulerDriver;
	private ConcurrentHashMap<String, Offer> offerMap = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, TaskInfo> taskMap = new ConcurrentHashMap<>();
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
	public void sendFrameworkMessage(String taskId, byte[] data) {
		TaskInfo taskInfo = this.getTaskInfo(taskId);
		this.schedulerDriver.sendFrameworkMessage(
				taskInfo.getExecutor().getExecutorId(), 
				taskInfo.getSlaveId(), data);
	}

	@Override
	public void killTask(String taskId) {
		Protos.TaskID.Builder b = Protos.TaskID.newBuilder();
		b.setValue(taskId);
		this.schedulerDriver.killTask(b.build());
	}
	private TaskInfo composeTaskInfo(Offer o, TaskSpec taskSpec) {
		TaskInfo.Builder b = TaskInfo.newBuilder();

		return null;
	}
	@Override
	public void launchTasks(String jobId, TaskSpec taskSpec) {
		List<Offer> offers = queryOffers(taskSpec);
		for(Offer o: offers) {
			this.launchTask(o.getId(), composeTaskInfo(o, taskSpec));
		}
	}
	
	private List<Offer> queryOffers(TaskSpec taskSpec){
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
	private void launchTask(OfferID offerId, TaskInfo taskInfo) {
		this.schedulerDriver.launchTasks(Arrays.asList(offerId), Arrays.asList(taskInfo));
	}
	private TaskInfo getTaskInfo(String taskId) {
		return this.taskMap.get(taskId);
	}
}
