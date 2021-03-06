package io.wolverine.message.job;

import java.util.Map;

public class SubmitJobMsg {
	private Integer tasks;
	private Integer runningTasks;
	private Integer mem;
	private Integer cores;
	private Integer disk;
	private Integer mode;
	private Integer taskType;
	private String imageUri; // Or image-and-tag
	private String entryPoint; // for docker or simple command
	private String target;
	private Map<String, String> options;
	public Integer getTasks() {
		return tasks;
	}
	public void setTasks(Integer tasks) {
		this.tasks = tasks;
	}
	public Integer getRunningTasks() {
		return runningTasks;
	}
	public void setRunningTasks(Integer runningTasks) {
		this.runningTasks = runningTasks;
	}
	public Integer getMem() {
		return mem;
	}
	public void setMem(Integer mem) {
		this.mem = mem;
	}
	public Integer getCores() {
		return cores;
	}
	public void setCores(Integer cores) {
		this.cores = cores;
	}
	public Integer getDisk() {
		return disk;
	}
	public void setDisk(Integer disk) {
		this.disk = disk;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return "SubmitJobMsg [tasks=" + tasks + ", runningTasks=" + runningTasks + ", mem=" + mem + ", cores=" + cores
				+ ", disk=" + disk + ", mode=" + mode + ", taskType=" + taskType + ", imageUri=" + imageUri
				+ ", entryPoint=" + entryPoint + ", target=" + target + ", options=" + options + "]";
	}
	public SubmitJobMsg() {
		super();
	}
}
