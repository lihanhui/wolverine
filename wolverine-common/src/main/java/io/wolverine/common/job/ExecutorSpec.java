package io.wolverine.common.job;

public class ExecutorSpec {
	private String archiveUri;
	private String command;
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
		return "ExecutorSpec [archiveUri=" + archiveUri + ", command=" + command + "]";
	}
	public ExecutorSpec(String archiveUri, String command) {
		super();
		this.archiveUri = archiveUri;
		this.command = command;
	}
}
