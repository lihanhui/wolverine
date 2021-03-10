package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import io.doraemon.json.JsonUtil;
import io.wolverine.container.docker.DockerContainer;
import io.wolverine.container.docker.HostConfig;
import io.wolverine.container.docker.SimpleDockerContainer;
import io.wolverine.proto.WolverineProto;
import io.wolverine.proto.WolverineProto.CommandType;
import io.wolverine.proto.WolverineProto.CreateTaskMsg;
import io.wolverine.proto.WolverineProto.TaskIdMsg;
import io.wolverine.proto.WolverineProto.WolverineTaskMsg;

public class DockerTaskSpec {
	private static SimpleDockerContainer container = new SimpleDockerContainer();
	private String containerId;
	private HostConfig hostConfig;
	private TaskInfo taskInfo;
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
	public TaskInfo getTaskInfo() {
		return taskInfo;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder{
		private DockerTaskSpec taskSpec;
		private TaskInfo taskInfo;
		
		private Builder(){}
		private void build(CreateTaskMsg msg) {
			HostConfig.Builder b = HostConfig.builder();
			if(msg.hasCommand()) b.withCmd(msg.getCommand());
			if(msg.hasCores()) b.withCpuCount(Long.valueOf(msg.getCores()));
			if(msg.hasMem()) b.withMemory(Long.valueOf(msg.getMem()));
			// TODO: entry point ...
			this.taskSpec.hostConfig = b.build();
			this.taskSpec.imageAndTag = msg.getImageAndTag();
		}
		private void build(TaskIdMsg msg) {
			taskSpec.containerId = msg.getContainerId();
		}
		private void build(TaskInfo taskInfo) {
			ByteString byteStr = taskInfo.getData();
			try {
				WolverineTaskMsg taskMsg = WolverineTaskMsg.parseFrom(taskInfo.getData());
				
				byteStr = taskMsg.getData();
				byte[] data = byteStr.asReadOnlyByteBuffer().array();
				String dataStr = data.toString();
				CommandType cmdType = taskMsg.getCommandType();
				switch(cmdType.getNumber()) {
					case CommandType.CREATE_TASK_VALUE:
						build(JsonUtil.fromJson(dataStr, CreateTaskMsg.class)); 
						break;
					default:
						build(JsonUtil.fromJson(dataStr, TaskIdMsg.class));
						break;
				}
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public Builder withTaskInfo(TaskInfo taskInfo) {
			this.taskInfo = taskInfo;
			return this;
		}
		
		public DockerTaskSpec build() {
			this.taskSpec.taskInfo = taskInfo;
			this.build(taskInfo);
			return taskSpec;
		}
	}
}
