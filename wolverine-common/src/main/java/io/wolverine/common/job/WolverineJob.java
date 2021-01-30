package io.wolverine.common.job;

import io.wolverine.common.message.TaskStatusMsg;

public interface WolverineJob {
	void statusUpdated(TaskStatusMsg status);
	String getJobId();
	TaskSpec getTaskSpec();
	String getTarget();
	boolean containerized();
}
