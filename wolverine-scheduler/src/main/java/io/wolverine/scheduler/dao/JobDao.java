package io.wolverine.scheduler.dao;

import io.wolverine.data.entity.Job;

public interface JobDao {
	Job get(String id);
	Job save(Job entity);
}
