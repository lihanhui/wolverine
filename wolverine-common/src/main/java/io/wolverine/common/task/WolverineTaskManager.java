package io.wolverine.common.task;

import org.apache.mesos.Protos.TaskID;
import org.apache.mesos.Protos.TaskInfo;
import org.apache.mesos.Protos.TaskState;

public interface WolverineTaskManager {
	public void sendFrameworkMsg(byte[] data);
	public void sendStatusUpdate(TaskID taskId, TaskState taskState, byte[] data);
}
