package io.wolverine.common.task;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskState;
import org.apache.mesos.Protos.TaskStatus;

import com.google.protobuf.ByteString;

import io.wolverine.common.executor.WolverineExecutorListener;

public abstract class AbstractWolverineTaskManager implements WolverineTaskManager, WolverineExecutorListener{
	private ExecutorDriver executorDriver;
	private ExecutorInfo executorInfo;
	private ConcurrentHashMap<String, WolverineTask> taskMap = new ConcurrentHashMap<>();
	
	public AbstractWolverineTaskManager(ExecutorDriver executorDriver) {
		this.executorDriver = executorDriver;
	}
	public void launchTask(TaskInfo task) {
		// TODO Auto-generated method stub
		
	}
	public ExecutorInfo getExecutorInfo() {
		return executorInfo;
	}
	public void setExecutorInfo(ExecutorInfo executorInfo) {
		this.executorInfo = executorInfo;
	}
	public void killTask(String taskId) {
		WolverineTask task = this.taskMap.get(taskId);
		WolverineTaskContext ctx = new DefaultWolverineTaskContext(null, task);
		task.stop(ctx);
	}

	public void killAllTasks() {
		for(WolverineTask task: this.taskMap.values()){
			WolverineTaskContext ctx = new DefaultWolverineTaskContext(null, task);
			task.stop(ctx);
		}
	}

	public void onFrameworkMsg(byte[] data) {
		// TODO Auto-generated method stub
		//executorDriver
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
