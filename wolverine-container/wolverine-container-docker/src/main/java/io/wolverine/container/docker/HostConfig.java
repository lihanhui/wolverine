package io.wolverine.container.docker;

import java.util.ArrayList;
import java.util.List;

public class HostConfig {
	private com.github.dockerjava.api.model.HostConfig hostConfig;
	private String[] envs;
	private String cmd;
	private String hostName;
	public static HostConfig builder() {
		return new HostConfig();
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
	public HostConfig withCmd(String cmd) {
		this.cmd = cmd;
		return this;
	}
	public HostConfig withHostName(String hostName) {
		this.hostName = hostName; 
		return this;
	}
	public HostConfig withCpuCount(Long cpuCount) {
		this.hostConfig.withCpuCount(cpuCount);
		return this;
	}
	public HostConfig withDiskQuota(Long diskQuota) {
		this.hostConfig.withDiskQuota(diskQuota);
		return this;
	}
	public HostConfig withMemory(Long memory){
		this.hostConfig.withMemory(memory);
		return this;
	}
	public HostConfig withMemorySwap(Long memorySwap) {
		this.hostConfig.withMemorySwap(memorySwap);
		return this;
	}
	public HostConfig withDns(String... dnses) {
		this.hostConfig.withDns(dnses);
        return this;
    }
	public HostConfig withDns(List<String> dnses) {
		this.hostConfig.withDns(dnses);
    	return this;
    }
    public HostConfig withUlimits(Ulimit[] ulimits) {
    	com.github.dockerjava.api.model.Ulimit[] us = new com.github.dockerjava.api.model.Ulimit[ulimits.length];
    	for(int i = 0; i < ulimits.length; ++i) {
    		us[i] = new com.github.dockerjava.api.model.Ulimit(ulimits[i].getName(), ulimits[i].getSoft(), ulimits[i].getHard());
    	}
    	this.hostConfig.withUlimits(us);
        return this;
    }
    public HostConfig withUlimits(List<Ulimit> ulimits) {
    	List<com.github.dockerjava.api.model.Ulimit> us = new ArrayList<>(ulimits.size());
    	for(Ulimit u : ulimits) {
    		us.add(new com.github.dockerjava.api.model.Ulimit(u.getName(), u.getSoft(), u.getHard()));
    	}
    	this.hostConfig.withUlimits(us);
    	return this;
    }
    public HostConfig withEnv(String... envs) {
    	this.envs = envs; 
    	return this;
    }
    public HostConfig withEnv(List<String> envs) {
    	return withEnv(envs.toArray(new String[0]));
    }
    /*
     * host mode: default, no port binding takes effect
     * */
    public HostConfig withNetworkMode(String networkMode) {
    	this.hostConfig.withNetworkMode("host");
    	return this;
    }
    public HostConfig build() {
    	return this;
    }
}
