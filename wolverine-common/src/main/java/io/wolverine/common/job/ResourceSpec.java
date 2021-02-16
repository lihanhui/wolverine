package io.wolverine.common.job;

public class ResourceSpec {
	private int cores;
	private int memory; //M Bytes
	private int disk;   //M Bytes
	public int getCores() {
		return cores;
	}
	public void setCores(int cores) {
		this.cores = cores;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public int getDisk() {
		return disk;
	}
	public void setDisk(int disk) {
		this.disk = disk;
	}
	@Override
	public String toString() {
		return "ResourceLimit [cores=" + cores + ", memory=" + memory + ", disk=" + disk + "]";
	}
	public ResourceSpec(int cores, int memory, int disk) {
		super();
		this.cores = cores;
		this.memory = memory;
		this.disk = disk;
	}
}
