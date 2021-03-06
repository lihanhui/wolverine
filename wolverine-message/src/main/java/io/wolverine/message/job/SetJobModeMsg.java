package io.wolverine.message.job;

public class SetJobModeMsg {
	private String jobId;
	private Integer mode; //维护，生产，测试
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "SetJobModeMsg [jobId=" + jobId + ", mode=" + mode + "]";
	}
	public SetJobModeMsg() {
		super();
	}
}
