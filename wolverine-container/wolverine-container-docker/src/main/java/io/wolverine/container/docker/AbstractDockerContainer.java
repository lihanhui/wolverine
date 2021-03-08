package io.wolverine.container.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public abstract class AbstractDockerContainer implements DockerContainer {
	private DockerClient dockerClient ;
	public AbstractDockerContainer() {
		DockerClientConfig config 
	  		= DefaultDockerClientConfig.createDefaultConfigBuilder()
	  		.withDockerHost("unix:///var/run/docker.sock")
	  		.build();
		dockerClient = DockerClientBuilder
			.getInstance(config)
			.build();
	}
	@Override
	public void stop(String containerId) {
		dockerClient.stopContainerCmd(containerId).exec();
	}

	@Override
	public void start(String containerId) {
		dockerClient.startContainerCmd(containerId).exec();
	}

	@Override
	public void kill(String containerId) {
		dockerClient.killContainerCmd(containerId).exec();
	}

	@Override
	public String create(String imageAndTag, HostConfig hostConfig) {
		CreateContainerResponse response = dockerClient.createContainerCmd(imageAndTag)
			.withHostConfig(hostConfig.getHostConfig())
			.withCmd(hostConfig.getCmd())
			.withHostName(hostConfig.getHostName())
			.withEnv(hostConfig.getEnvs())
			.exec();
		return response.getId();
	}

}
