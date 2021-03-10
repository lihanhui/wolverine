package io.wolverine.executor;

import org.apache.mesos.ExecutorDriver;

import io.wolverine.common.task.AbstractWolverineTaskManager;
import io.wolverine.common.task.WolverineTask;
import io.wolverine.executor.task.TaskFactory;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class SimpleWolverineTaskManager extends AbstractWolverineTaskManager {

	public SimpleWolverineTaskManager(ExecutorDriver executorDriver) {
		super(executorDriver);
	}

	@Override
	protected WolverineTask buildTask(WolverineTaskMsg msg) {
		return TaskFactory.build(msg);
	}

}
