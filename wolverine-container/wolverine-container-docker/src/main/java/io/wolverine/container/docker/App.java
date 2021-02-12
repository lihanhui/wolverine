package io.wolverine.container.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DefaultDockerClientConfig.Builder config 
    	  = DefaultDockerClientConfig.createDefaultConfigBuilder();
    	DockerClient dockerClient = DockerClientBuilder
    	  .getInstance(config)
    	  .build();
    	//dockerClient.createContainerCmd("").withcon.stopContainerCmd(containerId)
    	//dockerClient.createContainerCmd(arg0);
    	dockerClient.createContainerCmd("mongo:3.6")
        .withCmd("--bind_ip_all")
        .withName("mongo")
        .withHostName("baeldung")
        .withEnv("MONGO_LATEST_VERSION=3.6")
        .withPortBindings(PortBinding.parse("9999:27017"))
        .withBinds(Bind.parse("/Users/baeldung/mongo/data/db:/data/db")).exec();
    	
    	System.out.println( "Hello World!" );
    }
}
