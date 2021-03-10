package io.wolverine.data.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="wolverine_job")
public class Job {
	@Id
	private String  jobId;
	private String  jobName;
	private Integer tasks;
	private Integer runningTasks;
	private Integer mem;
	private Integer cores;
	private Integer disk;
	private Integer mode;
	private Integer status;
	private Integer taskType;
	private String imageUri; // Or image-and-tag
	private String target;
	private Map<String, String> options;
	private Long createDate;
	private Long updateDate;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
	
}
