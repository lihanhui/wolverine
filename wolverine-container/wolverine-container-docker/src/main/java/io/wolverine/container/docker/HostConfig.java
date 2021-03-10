package io.wolverine.container.docker;

import java.util.ArrayList;
import java.util.List;

public class HostConfig {
	private com.github.dockerjava.api.model.HostConfig hostConfig;
	private String[] envs;
	private String cmd;
	private String hostName;
	public static Builder builder() {
		HostConfig config = new HostConfig();
		return config.new Builder();
	}
	public com.github.dockerjava.api.model.HostConfig getHostConfig() {
		return hostConfig;
	}
	public String[] getEnvs() {
		return envs;
	}
	public String getCmd() {
		return cmd;
	}
	public String getHostName() {
		return hostName;
	}
	private HostConfig() {
		this.hostConfig = new com.github.dockerjava.api.model.HostConfig();
		this.envs = null;
	}
    
    public class Builder{
    	//private HostConfig hostConfig;
    	private Builder() {
    		//this.hostConfig = new HostConfig();
    	}
    	public HostConfig build() {
        	return HostConfig.this;
        }
    	public Builder withCmd(String cmd) {
    		HostConfig.this.cmd = cmd;
    		return this;
    	}
    	public Builder withHostName(String hostName) {
    		HostConfig.this.hostName = hostName; 
    		return this;
    	}
    	public Builder withCpuCount(Long cpuCount) {
    		HostConfig.this.hostConfig.withCpuCount(cpuCount);
    		return this;
    	}
    	public Builder withDiskQuota(Long diskQuota) {
    		HostConfig.this.hostConfig.withDiskQuota(diskQuota);
    		return this;
    	}
    	public Builder withMemory(Long memory){
    		HostConfig.this.hostConfig.withMemory(memory);
    		return this;
    	}
    	public Builder withMemorySwap(Long memorySwap) {
    		HostConfig.this.hostConfig.withMemorySwap(memorySwap);
    		return this;
    	}
    	public Builder withDns(String... dnses) {
    		HostConfig.this.hostConfig.withDns(dnses);
            return this;
        }
    	public Builder withDns(List<String> dnses) {
    		HostConfig.this.hostConfig.withDns(dnses);
        	return this;
        }
        public Builder withUlimits(Ulimit[] ulimits) {
        	com.github.dockerjava.api.model.Ulimit[] us = new com.github.dockerjava.api.model.Ulimit[ulimits.length];
        	for(int i = 0; i < ulimits.length; ++i) {
        		us[i] = new com.github.dockerjava.api.model.Ulimit(ulimits[i].getName(), ulimits[i].getSoft(), ulimits[i].getHard());
        	}
        	HostConfig.this.hostConfig.withUlimits(us);
            return this;
        }
        public Builder withUlimits(List<Ulimit> ulimits) {
        	List<com.github.dockerjava.api.model.Ulimit> us = new ArrayList<>(ulimits.size());
        	for(Ulimit u : ulimits) {
        		us.add(new com.github.dockerjava.api.model.Ulimit(u.getName(), u.getSoft(), u.getHard()));
        	}
        	HostConfig.this.hostConfig.withUlimits(us);
        	return this;
        }
        public Builder withEnv(String... envs) {
        	HostConfig.this.envs = envs; 
        	return this;
        }
        public Builder withEnv(List<String> envs) {
        	return withEnv(envs.toArray(new String[0]));
        }
        /*
         * host mode: default, no port binding takes effect
         * */
        public Builder withNetworkMode(String networkMode) {
        	HostConfig.this.hostConfig.withNetworkMode("host");
        	return this;
        }
    }
    
    
}
