package io.wolverine.container.docker;

public interface DockerContainer {
	void stop(String containerId);
	void start(String containerId);
	void kill(String containerId);
	String create(String imageAndTag, HostConfig hostConfig);
}
