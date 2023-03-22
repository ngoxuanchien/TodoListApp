package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
