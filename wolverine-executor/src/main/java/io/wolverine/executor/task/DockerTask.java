package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.common.task.WolverineTaskContext;
import io.wolverine.container.docker.DockerContainer;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DockerTask implements WolverineTask{
	private DockerContainer container ;
	public DockerTask(DockerContainer container) {
		this.container = container;
	}
	@Override
	public void start(WolverineTaskContext ctx) {
		// TODO Auto-generated method stub
		//container.create(ctx.g, hostConfig)
	}

	@Override
	public void stop(WolverineTaskContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WolverineTaskContext ctx, WolverineTaskMsg msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaskInfo getTaskInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
