package io.wolverine.executor.task;

import org.apache.mesos.Protos.TaskInfo;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import io.doraemon.json.JsonUtil;
import io.wolverine.container.docker.DockerContainer;
import io.wolverine.container.docker.HostConfig;
import io.wolverine.container.docker.SimpleDockerContainer;
import io.wolverine.proto.WolverineProto;
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
		Builder(){
			
		}
		private String imageAndTag(TaskInfo taskInfo) {
			return null;
		}
		private HostConfig buildHostConfig(TaskInfo taskInfo) {
			ByteString byteStr = taskInfo.getData();
			try {
				WolverineTaskMsg taskMsg = WolverineTaskMsg.parseFrom(taskInfo.getData());
				
				byteStr = taskMsg.getData();
				byte[] data = byteStr.asReadOnlyByteBuffer().array();
				String dataStr = data.toString();
				WolverineProto.CommandType cmdType = taskMsg.getCommandType();
				JsonUtil.fromJson(dataStr, WolverineTaskMsg.class);
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
			return null;
		}
		public Builder withTaskInfo(TaskInfo taskInfo) {
			this.taskInfo = taskInfo;
			return this;
		}
		
		public DockerTaskSpec build() {
			this.taskSpec.taskInfo = taskInfo;
			HostConfig hostConfig = this.buildHostConfig(taskInfo);
			this.taskSpec.hostConfig = hostConfig;
			this.taskSpec.imageAndTag = imageAndTag(taskInfo);
			return taskSpec;
		}
	}
}
