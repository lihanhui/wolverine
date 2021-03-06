package io.wolverine.message.job;

public class JobIdMsg {
	private String jobId;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	@Override
	public String toString() {
		return "JobIdMsg [jobId=" + jobId + "]";
	}
	public JobIdMsg() {
		super();
	}
}
