package io.wolverine.scheduler.repository;

import org.springframework.data.repository.CrudRepository;

import io.wolverine.data.entity.Job;

public interface JobRepository  extends CrudRepository<Job, String>{

}
