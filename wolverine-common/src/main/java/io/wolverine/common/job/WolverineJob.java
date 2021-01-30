package io.wolverine.common.job;

import io.wolverine.common.message.TaskStatus;

public interface WolverineJob {
	void statusUpdated(TaskStatus status);
	TaskSpec getTaskSpec();
}
