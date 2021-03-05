package io.wolverine.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="wolverine_task")
public class Task {
	@Id
	private String taskId;
	private String jobId;
	private String containerId;
	private String processId;
	
	private Integer status;
	private Integer mode;
	private Long createDate;
	private Long updateDate;
}
