package io.wolverine.message.task;

public class SubmitTaskMsg {
	private String jobId;
	private Integer mode;
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
		return "SubmitTaskMsg [jobId=" + jobId + ", mode=" + mode + "]";
	}
	public SubmitTaskMsg() {
		super();
	}
}
