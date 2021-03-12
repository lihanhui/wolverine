package io.wolverine.common.task;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskState;
import org.apache.mesos.Protos.TaskStatus;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import io.wolverine.common.executor.WolverineExecutorListener;
import io.wolverine.proto.WolverineProto.CommandType;
import io.wolverine.proto.WolverineProto.DataType;
import io.wolverine.proto.WolverineProto.HeartBeatMsg;
import io.wolverine.proto.WolverineProto.TaskType;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public abstract class AbstractWolverineTaskManager implements WolverineTaskManager, WolverineExecutorListener{
	private ExecutorDriver executorDriver;
	private ExecutorInfo executorInfo;
	private ConcurrentHashMap<String, WolverineTask> taskMap = new ConcurrentHashMap<>();
	
	public AbstractWolverineTaskManager(ExecutorDriver executorDriver) {
		this.executorDriver = executorDriver;
	}
	public void launchTask(TaskInfo task) {
		try {
			WolverineTaskMsg msg = WolverineTaskMsg.parseFrom(task.getData());
			WolverineTask task2 = buildTask(msg);
			WolverineTaskContext ctx = new DefaultWolverineTaskContext(this, task2);
			task2.create(ctx);
			task2.start(ctx);
			WolverineTaskMsg.Builder b = WolverineTaskMsg.newBuilder();
			b.setJobId(msg.getJobId());
			b.setTaskId(msg.getTaskId());
			b.setCommandType(CommandType.HEART_BEAT);
			b.setTaskType(msg.getTaskType());
			b.setDataType(DataType.PROTOBUF);
			b.setData(
					HeartBeatMsg.newBuilder()
					.setJobId(msg.getJobId())
					.setTaskId(msg.getTaskId())
					.build()
					.toByteString());
			this.executorDriver.sendFrameworkMessage(b.build().toByteArray());
			this.taskMap.put(task.getTaskId().getValue(), task2);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract WolverineTask buildTask(WolverineTaskMsg msg);
	
	public ExecutorInfo getExecutorInfo() {
		return executorInfo;
	}
	public void setExecutorInfo(ExecutorInfo executorInfo) {
		this.executorInfo = executorInfo;
	}
	public void killTask(String taskId) {
		WolverineTask task = this.taskMap.get(taskId);
		WolverineTaskContext ctx = new DefaultWolverineTaskContext(this, task);
		task.stop(ctx);
	}

	public void killAllTasks() {
		for(WolverineTask task: this.taskMap.values()){
			WolverineTaskContext ctx = new DefaultWolverineTaskContext(this, task);
			task.stop(ctx);
		}
	}

	public void onFrameworkMsg(byte[] data) {
		try {
			WolverineTaskMsg msg = WolverineTaskMsg.parseFrom(data);
			WolverineTask task = this.taskMap.get(msg.getTaskId());
			if(task == null) {
				task = this.buildTask(msg);
			}
			task.onMessage(new DefaultWolverineTaskContext(this, task), msg);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendFrameworkMsg(byte[] data) {
		executorDriver.sendFrameworkMessage(data);
	}

	public void sendStatusUpdate(TaskID taskId, TaskState taskState, byte[] data) {
		TaskStatus.Builder b = TaskStatus.newBuilder();
		b.setTaskId(taskId);
		b.setState(taskState);
		b.setData(ByteString.copyFrom(data));
		executorDriver.sendStatusUpdate(b.build());
	}

}
