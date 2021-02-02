package io.wolverine.common.task;

import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public interface WolverineTask {
	void start(WolverineTaskContext ctx);
	void stop(WolverineTaskContext ctx);
	void onMessage(WolverineTaskContext ctx, WolverineTaskMsg msg);
	TaskInfo getTaskInfo();
}
