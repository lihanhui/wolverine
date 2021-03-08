package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import io.wolverine.container.docker.DockerContainer;
import io.wolverine.container.docker.HostConfig;

public class DockerTaskSpec {
	private DockerContainer container;
	private HostConfig hostConfig;
	private TaskInfo taskInfo;
	private String imageAndTag;
	private DockerTaskSpec() {
		
	}
	public DockerContainer getContainer() {
		return container;
	}
	public HostConfig getHostConfig() {
		return hostConfig;
	}
	public String getImageAndTag() {
		return imageAndTag;
	}
	public TaskInfo getTaskInfo() {
		return taskInfo;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private DockerTaskSpec taskSpec;
		private TaskInfo taskInfo;
		Builder(){
			
		}
		public Builder withTaskInfo(TaskInfo taskInfo) {
			this.taskInfo = taskInfo;
			return this;
		}
		public DockerTaskSpec build() {
			this.taskSpec.taskInfo = taskInfo;
			return taskSpec;
		}
	}
}
