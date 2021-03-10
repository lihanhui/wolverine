package io.wolverine.common.task;

import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public interface WolverineTask {
	void create(WolverineTaskContext ctx);
	void start(WolverineTaskContext ctx);
	void stop(WolverineTaskContext ctx);
	void onMessage(WolverineTaskContext ctx, WolverineTaskMsg msg);
	String getTaskId();
}
