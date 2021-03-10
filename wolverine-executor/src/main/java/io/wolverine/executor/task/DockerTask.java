package io.wolverine.executor.task;

import io.wolverine.common.task.WolverineTask;
import io.wolverine.common.task.WolverineTaskContext;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DockerTask implements WolverineTask{
	private String containerId;
	private DockerTaskSpec taskSpec;
	public DockerTask(DockerTaskSpec taskSpec) {
		this(taskSpec, null);
	}
	public DockerTask(DockerTaskSpec taskSpec, String containerId) {
		this.taskSpec = taskSpec;
		this.containerId = containerId;
	}
	@Override
	public void start(WolverineTaskContext ctx) {
		if(containerId != null ){		
			taskSpec.getContainer().start(containerId);
		}else {
			taskSpec.getContainer().start(this.taskSpec.getContainerId());
		}
	}

	@Override
	public void stop(WolverineTaskContext ctx) {
		if(containerId != null ){		
			taskSpec.getContainer().stop(containerId);
		}else {
			taskSpec.getContainer().stop(this.taskSpec.getContainerId());
		}
	}

	@Override
	public void onMessage(WolverineTaskContext ctx, WolverineTaskMsg msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTaskId() {
		return taskSpec.getTaskId();
	}
	@Override
	public void create(WolverineTaskContext ctx) {
		containerId = taskSpec.getContainer().create(taskSpec.getImageAndTag(), taskSpec.getHostConfig());
	}

}
