package io.wolverine.common.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.SlaveInfo;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.WolverineTaskManager;

public abstract class AbstractWolverineExecutor implements WolverineExecutor {
	private WolverineExecutorListener executorListener;
	private ExecutorEnv executorEnv = new ExecutorEnv();
	
	public AbstractWolverineExecutor(WolverineExecutorListener executorListener) {
		this.executorListener = executorListener;
	}
	
	public WolverineExecutorListener getExecutorListener() {
		return executorListener;
	}

	public void setExecutorListener(WolverineExecutorListener executorListener) {
		this.executorListener = executorListener;
	}

	public void registered(ExecutorDriver driver, ExecutorInfo executorInfo, FrameworkInfo frameworkInfo,
			SlaveInfo slaveInfo) {
		this.executorEnv.setExecutorInfo(executorInfo);
		this.executorEnv.setFrameworkInfo(frameworkInfo);
		this.executorEnv.setSlaveInfo(slaveInfo);
		System.out.println(executorInfo);
		System.out.println(frameworkInfo);
		System.out.println(slaveInfo);
	}

	public void reregistered(ExecutorDriver driver, SlaveInfo slaveInfo) {
		this.executorEnv.setSlaveInfo(slaveInfo);
	}

	public void disconnected(ExecutorDriver driver) {
		// TODO Auto-generated method stub

	}

	public void launchTask(ExecutorDriver driver, TaskInfo task) {
		System.out.println("this is incoming: " + task);
		this.executorListener.launchTask(task);
	}

	public void killTask(ExecutorDriver driver, TaskID taskId) {
		this.executorListener.killTask(taskId.getValue());
	}

	public void frameworkMessage(ExecutorDriver driver, byte[] data) {
		this.executorListener.onFrameworkMsg(data);
	}

	public void shutdown(ExecutorDriver driver) {
		this.executorListener.killAllTasks();
	}

	public void error(ExecutorDriver driver, String message) {
		// TODO Auto-generated method stub
		// logger
	}

}
