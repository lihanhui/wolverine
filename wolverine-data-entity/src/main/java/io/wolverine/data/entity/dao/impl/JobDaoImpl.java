package io.wolverine.data.entity.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.wolverine.data.entity.Job;
import io.wolverine.data.entity.dao.JobDao;
import io.wolverine.data.entity.repository.JobRepository;
@Repository
public class JobDaoImpl implements JobDao {
	private @Autowired JobRepository repository;
	@Override
	public Job get(String id) {
		Optional<Job> optional = repository.findById(id);
		return optional.isPresent()? optional.get(): null;
	}

	@Override
	public Job save(Job entity) {
		return repository.save(entity);
	}

}
