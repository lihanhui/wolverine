package io.wolverine.common.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.SlaveInfo;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.WolverineTaskManager;

public abstract class AbstractWolverineExecutor implements WolverineExecutor {
	private WolverineTaskManager taskManager;
	private ExecutorEnv executorEnv;
	
	public AbstractWolverineExecutor(WolverineTaskManager taskManager) {
		this.taskManager = taskManager;
	}
	
	public WolverineTaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(WolverineTaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public void registered(ExecutorDriver driver, ExecutorInfo executorInfo, FrameworkInfo frameworkInfo,
			SlaveInfo slaveInfo) {
		this.executorEnv.setExecutorInfo(executorInfo);
		this.executorEnv.setFrameworkInfo(frameworkInfo);
		this.executorEnv.setSlaveInfo(slaveInfo);
	}

	public void reregistered(ExecutorDriver driver, SlaveInfo slaveInfo) {
		this.executorEnv.setSlaveInfo(slaveInfo);
	}

	public void disconnected(ExecutorDriver driver) {
		// TODO Auto-generated method stub

	}

	public void launchTask(ExecutorDriver driver, TaskInfo task) {
		this.taskManager.launchTask(task);
	}

	public void killTask(ExecutorDriver driver, TaskID taskId) {
		this.taskManager.killTask(taskId.getValue());
	}

	public void frameworkMessage(ExecutorDriver driver, byte[] data) {
		this.taskManager.onFrameworkMsg(data);
	}

	public void shutdown(ExecutorDriver driver) {
		this.taskManager.killAllTasks();
	}

	public void error(ExecutorDriver driver, String message) {
		// TODO Auto-generated method stub
		// logger
	}

}
