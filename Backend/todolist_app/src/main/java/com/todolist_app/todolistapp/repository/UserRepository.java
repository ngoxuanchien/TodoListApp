package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.model.Task;
import com.todolist_app.todolistapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query (value = "SELECT t.id, t.task, t.user_id FROM tasks t " +
            "JOIN users u on t.user_id = u.id " +
            "WHERE u.email = :email", nativeQuery = true)
    Collection<Task> findAllTaskByEmail(String email);
}
