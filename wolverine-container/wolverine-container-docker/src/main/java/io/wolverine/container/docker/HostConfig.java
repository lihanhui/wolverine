package io.wolverine.container.docker;

import java.util.List;

import com.github.dockerjava.api.model.Ulimit;

public class HostConfig {
	private com.github.dockerjava.api.model.HostConfig hostConfig;
	public static HostConfig builder() {
		return new HostConfig();
	}
	private HostConfig() {
		this.hostConfig = new com.github.dockerjava.api.model.HostConfig();
	}
	HostConfig withCpuCount(Long cpuCount) {
		return this;
	}
	HostConfig withDiskQuota(Long diskQuota) {
		return this;
	}
	HostConfig withMemory(Long memory){
		return this;
	}
	HostConfig withMemorySwap(Long memorySwap) {
		return this;
	}
	HostConfig withDns(String... dns) {
        return this;
    }
    HostConfig withDns(List<String> dns) {
    	return this;
    }
    HostConfig withUlimits(Ulimit[] ulimits) {
        return this;
    }
    public HostConfig withUlimits(List<Ulimit> ulimits) {
    	return this;
    }
    public HostConfig build() {
    	return this;
    }
}
