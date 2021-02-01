package io.wolverine.common.job;

import io.wolverine.proto.WolverineProto.TaskStatusMsg;

public interface WolverineJob {
	void statusUpdated(TaskStatusMsg status);
	String getJobId();
	TaskSpec getTaskSpec();
	String getTarget();
	boolean containerized();
}
