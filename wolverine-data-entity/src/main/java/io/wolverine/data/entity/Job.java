package io.wolverine.data.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="wolverine_job")
public class Job {
	@Id
	private String  jobId;
	private Integer tasks;
	private Integer runningTasks;
	private Integer mem;
	private Integer cores;
	private Integer disk;
	private Integer mode;
	private Integer status;
	private Integer taskType;
	private String imageUri; // Or image-and-tag
	private String entryPoint; // for docker or simple command
	private String target;
	private Map<String, String> options;
	private Long createDate;
	private Long updateDate;
}
