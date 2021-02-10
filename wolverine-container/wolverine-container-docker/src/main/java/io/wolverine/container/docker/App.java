package io.wolverine.container.docker;

import com.github.dockerjava.api.DockerClient;
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
    	//dockerClient.createContainerCmd(arg0);
    	System.out.println( "Hello World!" );
    }
}
