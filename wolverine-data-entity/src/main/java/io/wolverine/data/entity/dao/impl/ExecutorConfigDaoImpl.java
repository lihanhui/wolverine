package io.wolverine.data.entity.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.wolverine.data.entity.ExecutorConfig;
import io.wolverine.data.entity.Task;
import io.wolverine.data.entity.dao.ExecutorConfigDao;
import io.wolverine.data.entity.repository.ExecutorConfigRepository;

@Repository
public class ExecutorConfigDaoImpl implements ExecutorConfigDao{
	private @Autowired ExecutorConfigRepository repository;
	@Override
	public ExecutorConfig get(String id) {
		Optional<ExecutorConfig> optional = repository.findById(id);
		return optional.isPresent()? optional.get(): null;
	}

	@Override
	public ExecutorConfig save(ExecutorConfig entity) {
		return repository.save(entity);
	}

}
