package io.wolverine.executor.task;

import com.google.protobuf.InvalidProtocolBufferException;

import io.wolverine.container.docker.DockerContainer;
import io.wolverine.container.docker.HostConfig;
import io.wolverine.container.docker.SimpleDockerContainer;
import io.wolverine.proto.WolverineProto.CommandType;
import io.wolverine.proto.WolverineProto.CreateTaskMsg;
import io.wolverine.proto.WolverineProto.TaskIdMsg;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DockerTaskSpec {
	private static SimpleDockerContainer container = new SimpleDockerContainer();
	private String containerId;
	private HostConfig hostConfig;
	private String taskId;
	private String imageAndTag;
	
	public String getContainerId() {
		return containerId;
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
	public String getTaskId() {
		return taskId;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private DockerTaskSpec taskSpec;
		private WolverineTaskMsg taskMsg;
		
		private Builder(){
			this.taskSpec = new DockerTaskSpec();
		}
		private void build(CreateTaskMsg msg) {
			HostConfig.Builder b = HostConfig.builder();
			b.withCmd("--bind_ip_all")
			.withHostName(msg.getIp())
			.withEnv("application.port="+msg.getPort())
			.withCpuCount(Long.valueOf(msg.getCores()))
			.withMemory(Long.valueOf(msg.getMem()))
			.withDiskQuota(Long.valueOf(msg.getDisk()));
			// TODO: entry point ...
			this.taskSpec.hostConfig = b.build();
			this.taskSpec.imageAndTag = msg.getImageAndTag();
		}
		private void build(TaskIdMsg msg) {
			taskSpec.containerId = msg.getContainerId();
		}
		private void build(WolverineTaskMsg taskMsg) {
			try {
				CommandType cmdType = taskMsg.getCommandType();
				switch(cmdType.getNumber()) {
					case CommandType.CREATE_TASK_VALUE:
						build(CreateTaskMsg.parseFrom(taskMsg.getData())); 
						break;
					default:
						build(TaskIdMsg.parseFrom(taskMsg.getData()));
						break;
				}
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public Builder withWolverineTaskMsg(WolverineTaskMsg taskMsg) {
			this.taskMsg = taskMsg;
			return this;
		}
		
		public DockerTaskSpec build() {
			this.build(taskMsg);
			return taskSpec;
		}
	}
}
