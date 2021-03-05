package io.wolverine.scheduler.repository;

import org.springframework.data.repository.CrudRepository;

import io.wolverine.data.entity.Task;

public interface TaskRepository   extends CrudRepository<Task, String>{

}
