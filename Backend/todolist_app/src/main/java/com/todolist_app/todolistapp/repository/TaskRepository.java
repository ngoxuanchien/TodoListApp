package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
