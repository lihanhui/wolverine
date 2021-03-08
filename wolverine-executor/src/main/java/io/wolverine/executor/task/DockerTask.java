package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.common.task.WolverineTaskContext;
import io.wolverine.container.docker.DockerContainer;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DockerTask implements WolverineTask{
	private String containerId;
	private DockerTaskSpec taskSpec;
	public DockerTask(DockerTaskSpec taskSpec) {
		this.taskSpec = taskSpec;
	}
	@Override
	public void start(WolverineTaskContext ctx) {
		containerId = taskSpec.getContainer().create(taskSpec.getImageAndTag(), taskSpec.getHostConfig());
	}

	@Override
	public void stop(WolverineTaskContext ctx) {
		taskSpec.getContainer().start(containerId);
	}

	@Override
	public void onMessage(WolverineTaskContext ctx, WolverineTaskMsg msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaskInfo getTaskInfo() {
		return this.taskSpec.getTaskInfo();
	}

}
