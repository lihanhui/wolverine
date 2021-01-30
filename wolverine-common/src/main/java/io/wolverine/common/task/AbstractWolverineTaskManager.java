package io.wolverine.common.task;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.TaskStatus;

import com.google.protobuf.ByteString;

import io.wolverine.common.message.TaskInfo;

public abstract class AbstractWolverineTaskManager implements WolverineTaskManager{
	private ExecutorDriver executorDriver;
	private ExecutorInfo executorInfo;
	
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
		// TODO Auto-generated method stub
		
	}

	public void killAllTasks() {
		// TODO Auto-generated method stub
		
	}

	public void frameworkMsg(byte[] data) {
		// TODO Auto-generated method stub
		//executorDriver
	}

	public void sendFrameworkMsg(byte[] data) {
		executorDriver.sendFrameworkMessage(data);
	}

	public void sendStatusUpdate(byte[] data) {
		TaskStatus.Builder b = TaskStatus.newBuilder();
		b.setData(ByteString.copyFrom(data));
		executorDriver.sendStatusUpdate(b.build());
	}

}
