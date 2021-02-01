package io.wolverine.common.task;

import org.apache.mesos.ExecutorDriver;

public class DefaultWolverineTaskManager extends AbstractWolverineTaskManager {

	public DefaultWolverineTaskManager(ExecutorDriver executorDriver) {
		super(executorDriver);
	}

}
