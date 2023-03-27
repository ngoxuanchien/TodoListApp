package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
