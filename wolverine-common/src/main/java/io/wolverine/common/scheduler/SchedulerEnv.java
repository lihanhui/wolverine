package io.wolverine.common.scheduler;

import org.apache.mesos.Protos.FrameworkID;
import org.apache.mesos.Protos.MasterInfo;

public class SchedulerEnv {
	private FrameworkID frameworkId;
	private MasterInfo masterInfo;
	public FrameworkID getFrameworkId() {
		return frameworkId;
	}
	public void setFrameworkId(FrameworkID frameworkId) {
		this.frameworkId = frameworkId;
	}
	public MasterInfo getMasterInfo() {
		return masterInfo;
	}
	public void setMasterInfo(MasterInfo masterInfo) {
		this.masterInfo = masterInfo;
	}
	@Override
	public String toString() {
		return "SchedulerEnv [frameworkId=" + frameworkId + ", masterInfo=" + masterInfo + "]";
	}
	
}
