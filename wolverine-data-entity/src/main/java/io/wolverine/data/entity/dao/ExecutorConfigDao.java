package io.wolverine.data.entity.dao;

import io.wolverine.data.entity.ExecutorConfig;

public interface ExecutorConfigDao {
	ExecutorConfig get(String id);
	ExecutorConfig save(ExecutorConfig entity);
}
