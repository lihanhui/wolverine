package io.wolverine.data.entity.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.wolverine.data.entity.Task;
import io.wolverine.data.entity.dao.TaskDao;
import io.wolverine.data.entity.repository.TaskRepository;

@Repository
public class TaskDaoImpl implements TaskDao{
	private @Autowired TaskRepository repository;
	@Override
	public Task get(String id) {
		Optional<Task> optional = repository.findById(id);
		return optional.isPresent()? optional.get(): null;
	}

	@Override
	public Task save(Task entity) {
		return repository.save(entity);
	}

}
