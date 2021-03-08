package io.wolverine.executor;

import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.AbstractWolverineTaskManager;
import io.wolverine.common.task.WolverineTask;
import io.wolverine.executor.task.TaskFactory;

public class SimpleWolverineTaskManager extends AbstractWolverineTaskManager {

	public SimpleWolverineTaskManager(ExecutorDriver executorDriver) {
		super(executorDriver);
	}

	@Override
	protected WolverineTask buildTask(TaskInfo task) {
		return TaskFactory.build(task);
	}

}
