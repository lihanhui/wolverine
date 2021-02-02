package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mesos.Protos;
import org.apache.mesos.Protos.CommandInfo;
import org.apache.mesos.Protos.ExecutorID;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.Offer;
import org.apache.mesos.Protos.OfferID;
import org.apache.mesos.Protos.Resource;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskStatus;
import org.apache.mesos.Protos.Value;
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
	private void composeResources(TaskInfo.Builder b, TaskSpec taskSpec) {
		Resource.Builder b2 = Resource.newBuilder();
		b2.setName("mem");
		b2.setType(Value.Type.SCALAR);
		Value.Scalar.Builder b21 = Value.Scalar.newBuilder();
		b21.setValue(taskSpec.getMemory());
		b2.setScalar(b21);
		b.addResources(b2);
		
		Resource.Builder b3 = Resource.newBuilder();
		b3.setName("cpus");
		b3.setType(Value.Type.SCALAR);
		Value.Scalar.Builder b32 = Value.Scalar.newBuilder();
		b32.setValue(taskSpec.getCores());
		b3.setScalar(b32);
		b.addResources(b3);
		
		Resource.Builder b4 = Resource.newBuilder();
		b4.setName("disk");
		b4.setType(Value.Type.SCALAR);
		Value.Scalar.Builder b42 = Value.Scalar.newBuilder();
		b42.setValue(taskSpec.getDisk());
		b4.setScalar(b42);
		b.addResources(b4);
	}
	private TaskInfo composeTaskInfo(Offer o, TaskSpec taskSpec) {
		TaskInfo.Builder b = TaskInfo.newBuilder();
		b.setName("my job");
		
		TaskID.Builder b1 = TaskID.newBuilder();
		b1.setValue("my-task-id");
		b.setTaskId(b1);
		
		b.setSlaveId(o.getSlaveId());
		
		ExecutorInfo.Builder b2 = ExecutorInfo.newBuilder();
		ExecutorID.Builder b22 = ExecutorID.newBuilder();
		b22.setValue("my-executor-id");
		b2.setExecutorId(b22);
		b2.setType(ExecutorInfo.Type.CUSTOM);
		CommandInfo.Builder b23 = CommandInfo.newBuilder();
		b23.setValue("ls -lt /");
		b2.setCommand(b23);
		b.setExecutor(b2);
		
		composeResources(b, taskSpec);
		return b.build();
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
