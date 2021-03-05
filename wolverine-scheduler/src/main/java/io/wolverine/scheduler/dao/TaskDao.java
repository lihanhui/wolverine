package io.wolverine.scheduler.dao;

import io.wolverine.data.entity.Task;

public interface TaskDao {
	Task get(String id);
	Task save(Task entity);
}
