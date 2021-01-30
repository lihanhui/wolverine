package io.wolverine.common.job;

import org.apache.mesos.Protos.TaskStatus;

public interface WolverineJob {
	void statusUpdated(TaskStatus status);
	TaskSpec getTaskSpec();
}
