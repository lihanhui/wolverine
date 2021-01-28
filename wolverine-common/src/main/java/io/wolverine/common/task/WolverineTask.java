package io.wolverine.common.task;

public interface WolverineTask {
	void start(WolverineTaskContext ctx);
	void stop(WolverineTaskContext ctx);
	void onMessage(WolverineTaskContext ctx, byte[] msg);
	TaskInfo getTaskInfo();
}
