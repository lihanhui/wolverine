package io.wolverine.common.job;

public class TaskSpec {
	private int cores;
	private int memory; //M Bytes
	private int disk;   //M Bytes
	private int tasks;	//任务数

	public int getCores() {
		return cores;
	}
	public int getMemory() {
		return memory;
	}
	public int getDisk() {
		return disk;
	}
	public int getTasks() {
		return tasks;
	}
	@Override
	public String toString() {
		return "TaskSpec [cores=" + cores + ", memory=" + memory + ", disk=" + disk + ", tasks=" + tasks + "]";
	}
	public TaskSpec(int cores, int memory, int disk, int tasks) {
		super();
		this.cores = cores;
		this.memory = memory;
		this.disk = disk;
		this.tasks = tasks;
	}
}
