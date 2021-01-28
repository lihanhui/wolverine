package io.wolverine.common.executor;

import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.SlaveInfo;

public class ExecutorEnv {
	private ExecutorInfo executorInfo;
	private FrameworkInfo frameworkInfo;
	private SlaveInfo slaveInfo;
	public ExecutorInfo getExecutorInfo() {
		return executorInfo;
	}
	public void setExecutorInfo(ExecutorInfo executorInfo) {
		this.executorInfo = executorInfo;
	}
	public FrameworkInfo getFrameworkInfo() {
		return frameworkInfo;
	}
	public void setFrameworkInfo(FrameworkInfo frameworkInfo) {
		this.frameworkInfo = frameworkInfo;
	}
	public SlaveInfo getSlaveInfo() {
		return slaveInfo;
	}
	public void setSlaveInfo(SlaveInfo slaveInfo) {
		this.slaveInfo = slaveInfo;
	}
	@Override
	public String toString() {
		return "ExecutorEnv [executorInfo=" + executorInfo + ", frameworkInfo=" + frameworkInfo + ", slaveInfo="
				+ slaveInfo + "]";
	}
}
