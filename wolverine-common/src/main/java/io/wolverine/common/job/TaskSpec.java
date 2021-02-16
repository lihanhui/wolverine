package io.wolverine.common.job;

public class TaskSpec {
	private ExecutorSpec executorSpec;
	private ResourceSpec resourceSpec;
	private String archiveUri;
	private String command;
	private int tasks;	//任务数
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
	@Override
	public String toString() {
		return "TaskSpec [executorSpec=" + executorSpec + ", resourceSpec=" + resourceSpec + ", archiveUri="
				+ archiveUri + ", command=" + command + ", tasks=" + tasks + "]";
	}
	public TaskSpec(ExecutorSpec executorSpec, ResourceSpec resourceSpec, String archiveUri, String command,
			int tasks) {
		super();
		this.executorSpec = executorSpec;
		this.resourceSpec = resourceSpec;
		this.archiveUri = archiveUri;
		this.command = command;
		this.tasks = tasks;
	}
}
