package io.wolverine.container.docker;

public class SimpleDockerContainer extends AbstractDockerContainer {
	public static void main(String [] args) {
    	HostConfig.Builder b = HostConfig.builder();
    	b.withCmd("");
    }
}
