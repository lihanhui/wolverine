package io.wolverine.common.task;

public interface WolverineTaskContext {
	void sendFrameworkMsg(byte[] data);
	void sendStatusUpdate(byte[] data);
}
