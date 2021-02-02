package io.wolverine.common.job;

import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public interface WolverineJob {
	void statusUpdated(WolverineTaskMsg msg);
	String getJobId();
	TaskSpec getTaskSpec();
	String getTarget();
	boolean containerized();
}
