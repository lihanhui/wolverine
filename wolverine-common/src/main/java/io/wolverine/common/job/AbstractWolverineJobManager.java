package io.wolverine.common.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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

import com.google.protobuf.ByteString;

import io.wolverine.common.scheduler.WolverineSchedulerListener;
import io.wolverine.proto.WolverineProto.CommandType;
import io.wolverine.proto.WolverineProto.CreateTaskMsg;
import io.wolverine.proto.WolverineProto.DataType;
import io.wolverine.proto.WolverineProto.TaskType;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public abstract class AbstractWolverineJobManager implements WolverineJobManager, WolverineSchedulerListener{
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
		b21.setValue(taskSpec.getResourceSpec().getMemory());
		b2.setScalar(b21);
		b.addResources(b2);
		
		Resource.Builder b3 = Resource.newBuilder();
		b3.setName("cpus");
		b3.setType(Value.Type.SCALAR);
		Value.Scalar.Builder b32 = Value.Scalar.newBuilder();
		b32.setValue(taskSpec.getResourceSpec().getCores());
		b3.setScalar(b32);
		b.addResources(b3);
		
		Resource.Builder b4 = Resource.newBuilder();
		b4.setName("disk");
		b4.setType(Value.Type.SCALAR);
		Value.Scalar.Builder b42 = Value.Scalar.newBuilder();
		b42.setValue(taskSpec.getResourceSpec().getDisk());
		b4.setScalar(b42);
		b.addResources(b4);
	}
	private CreateTaskMsg composeCreateTaskMsg(String taskId, TaskSpec taskSpec) {
		CreateTaskMsg.Builder b = CreateTaskMsg.newBuilder();
		b.setJobId(taskSpec.getJobId());
		b.setCores(taskSpec.getResourceSpec().getCores());
		b.setDisk(taskSpec.getResourceSpec().getDisk());
		b.setMem(taskSpec.getResourceSpec().getMemory());
		b.setImageAndTag(taskSpec.getArchiveUri());
		b.setImageUri(taskSpec.getArchiveUri());
		
		b.setTaskId(taskId);
		return b.build();
	}
	private WolverineTaskMsg composeWolverineTaskMsg(String taskId, TaskSpec taskSpec) {
		WolverineTaskMsg.Builder b = WolverineTaskMsg.newBuilder();
		b.setCommandType(CommandType.CREATE_TASK);
		b.setDataType(DataType.PROTOBUF);
		b.setJobId(taskSpec.getJobId());
		b.setTaskId(taskId);
		b.setTaskType(TaskType.forNumber(taskSpec.getTaskType()));
		b.setData(composeCreateTaskMsg(taskId, taskSpec).toByteString());
		return b.build();
	}
	private TaskInfo composeTaskInfo(Offer o, TaskSpec taskSpec) {
		String taskId = "taskId-" + String.valueOf(UUID.randomUUID());
		TaskInfo.Builder b = TaskInfo.newBuilder();
		TaskID.Builder b1 = TaskID.newBuilder();
		b1.setValue(taskId);
		b.setTaskId(b1);
		
		b.setName(taskSpec.getJobName());
		b.setSlaveId(o.getSlaveId());
		
		ExecutorInfo.Builder b2 = ExecutorInfo.newBuilder();
		ExecutorID.Builder b22 = ExecutorID.newBuilder();
		b22.setValue("wolverine-executor-" + o.getSlaveId().getValue());
		b2.setExecutorId(b22);				  //executorInfo.executorId	
		b2.setType(ExecutorInfo.Type.CUSTOM); //executorInfo.type
		CommandInfo.Builder b23 = CommandInfo.newBuilder();
		b23.setValue(taskSpec.getExecutorSpec().getCommand());
		
		CommandInfo.URI.Builder b231 = CommandInfo.URI.newBuilder();
		b231.setValue(taskSpec.getExecutorSpec().getArchiveUri());
		b23.addUris(b231);
		b2.setCommand(b23); //executorInfo.Command
		
		b2.setFrameworkId(o.getFrameworkId());
		
		b.setExecutor(b2);  // executorInfo
		
		composeResources(b, taskSpec);
		WolverineTaskMsg msg = this.composeWolverineTaskMsg(taskId, taskSpec);
		b.setData(ByteString.copyFrom(msg.toByteArray()));
		return b.build();
	}
	@Override
	public void launchTasks(TaskSpec taskSpec) {
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
				if("cpus".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getResourceSpec().getCores()) {
					ok = false;
					break;
				}
				if("mem".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getResourceSpec().getMemory()) {
					ok = false;
					break;
				}
				if("disk".equals(r.getName()) && r.getScalar().getValue() < taskSpec.getResourceSpec().getDisk()) {
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
		System.out.println(taskInfo);
		this.schedulerDriver.launchTasks(Arrays.asList(offerId), Arrays.asList(taskInfo));
	}
	private TaskInfo getTaskInfo(String taskId) {
		return this.taskMap.get(taskId);
	}
}
