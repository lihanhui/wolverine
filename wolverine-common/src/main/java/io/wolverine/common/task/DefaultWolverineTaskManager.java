package io.wolverine.common.task;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.TaskInfo;

public class DefaultWolverineTaskManager extends AbstractWolverineTaskManager {

	public DefaultWolverineTaskManager(ExecutorDriver executorDriver) {
		super(executorDriver);
	}

	@Override
	protected WolverineTask buildTask(TaskInfo task) {
		// TODO Auto-generated method stub
		return null;
	}

}
