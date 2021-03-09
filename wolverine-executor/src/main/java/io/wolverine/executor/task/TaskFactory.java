package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.container.docker.SimpleDockerContainer;

public class TaskFactory {
	
	public static WolverineTask build(TaskInfo task) {
		DockerTaskSpec taskSpec = DockerTaskSpec.builder()
				.withTaskInfo(task)
				.build();
		
		return new DockerTask(taskSpec);
	}
}
