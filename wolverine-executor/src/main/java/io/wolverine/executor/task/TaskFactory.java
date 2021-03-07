package io.wolverine.executor.task;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.container.docker.SimpleDockerContainer;

public class TaskFactory {
	private static SimpleDockerContainer container = new SimpleDockerContainer();
	public WolverineTask build() {
		return new DockerTask(container);
	}
}
