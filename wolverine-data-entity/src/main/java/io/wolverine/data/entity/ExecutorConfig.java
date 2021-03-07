package io.wolverine.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="wolverine_executor_config")
public class ExecutorConfig {
	@Id
	private String  executorConfigId = "executor_config";
	private String archiveUri;
	private String command;
	public String getExecutorConfigId() {
		return executorConfigId;
	}
	public void setExecutorConfigId(String executorConfigId) {
		this.executorConfigId = executorConfigId;
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
	@Override
	public String toString() {
		return "ExecutorConfig [executorConfigId=" + executorConfigId + ", archiveUri=" + archiveUri + ", command="
				+ command + "]";
	}
	public ExecutorConfig() {
		super();
	}
}
