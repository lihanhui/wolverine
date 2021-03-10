package io.wolverine.common.job;

import java.util.Map;

public class TaskSpec {
	private String jobId;
	private String jobName;
	private ExecutorSpec executorSpec;
	private ResourceSpec resourceSpec;
	private String archiveUri;
	private String command;
	private Integer taskType;
	private Map<String, String> options;
	private int tasks;	//任务数
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public ExecutorSpec getExecutorSpec() {
		return executorSpec;
	}
	public void setExecutorSpec(ExecutorSpec executorSpec) {
		this.executorSpec = executorSpec;
	}
	public ResourceSpec getResourceSpec() {
		return resourceSpec;
	}
	public void setResourceSpec(ResourceSpec resourceSpec) {
		this.resourceSpec = resourceSpec;
	}
	public String getArchiveUri() {
		return archiveUri;
	}
	public void setArchiveUri(String archiveUri) {
		this.archiveUri = archiveUri;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getTasks() {
		return tasks;
	}
	public void setTasks(int tasks) {
		this.tasks = tasks;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	public TaskSpec(String jobId, String jobName, ExecutorSpec executorSpec, ResourceSpec resourceSpec,
			String archiveUri, String command, Integer taskType, Map<String, String> options, int tasks) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.executorSpec = executorSpec;
		this.resourceSpec = resourceSpec;
		this.archiveUri = archiveUri;
		this.command = command;
		this.taskType = taskType;
		this.options = options;
		this.tasks = tasks;
	}
	public TaskSpec() {
		super();
	}
}
