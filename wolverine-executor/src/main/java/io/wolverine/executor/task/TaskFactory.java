package io.wolverine.executor.task;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class TaskFactory {
	public static WolverineTask build(WolverineTaskMsg msg) {
		DockerTaskSpec taskSpec = DockerTaskSpec.builder()
				.withWolverineTaskMsg(msg)
				.build();
		
		return new DockerTask(taskSpec);
	}
}
