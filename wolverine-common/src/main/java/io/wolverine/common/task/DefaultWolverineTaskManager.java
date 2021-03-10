package io.wolverine.common.task;

import org.apache.mesos.ExecutorDriver;

import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DefaultWolverineTaskManager extends AbstractWolverineTaskManager {

	public DefaultWolverineTaskManager(ExecutorDriver executorDriver) {
		super(executorDriver);
	}

	@Override
	protected WolverineTask buildTask(WolverineTaskMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
