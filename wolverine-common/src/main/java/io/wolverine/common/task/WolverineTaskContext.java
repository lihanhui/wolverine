package io.wolverine.common.task;

public interface WolverineTaskContext {
	void sendFrameworkMsg(byte[] data);
	
	void sendStartStatus(byte[] data);
	void sendStartedStatus(byte[] data);
	void sendRunningStatus(byte[] data);
	void sendFinishedStatus(byte[] data);
	
	void sendStopStatus(byte[] data);
	void sendStoppedStatus(byte[] data);
	
}
