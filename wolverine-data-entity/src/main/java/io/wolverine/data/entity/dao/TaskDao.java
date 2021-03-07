package io.wolverine.data.entity.dao;

import io.wolverine.data.entity.Task;

public interface TaskDao {
	Task get(String id);
	Task save(Task entity);
}
