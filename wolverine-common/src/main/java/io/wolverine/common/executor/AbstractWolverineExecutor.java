package io.wolverine.common.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.SlaveInfo;
import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.TaskManager;

public abstract class AbstractWolverineExecutor implements WolverineExecutor {
	private TaskManager taskManager;
	private ExecutorEnv executorEnv;
	
	public AbstractWolverineExecutor(TaskManager taskManager) {
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
		io.wolverine.common.task.TaskInfo taskInfo = new io.wolverine.common.task.TaskInfo(task);
		this.taskManager.launchTask(taskInfo);
	}

	public void killTask(ExecutorDriver driver, TaskID taskId) {
		this.taskManager.killTask(taskId.getValue());
	}

	public void frameworkMessage(ExecutorDriver driver, byte[] data) {
		this.taskManager.frameworkMsg(data);
	}

	public void shutdown(ExecutorDriver driver) {
		this.taskManager.killAllTasks();
	}

	public void error(ExecutorDriver driver, String message) {
		// TODO Auto-generated method stub
		// logger
	}

}
