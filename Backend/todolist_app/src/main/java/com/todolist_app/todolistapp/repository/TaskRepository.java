package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.model.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT t.id, t.title, t.user_id, t.description, t.created_time FROM tasks t " +
            "JOIN users u on t.user_id = u.id " +
            "WHERE u.email = :email", nativeQuery = true)
    Optional<ArrayList<Task>> getAllByEmail(String email);


    @Query(value = "SELECT * FROM tasks t WHERE t.user_id = :user_id", nativeQuery = true)
    List<Task> getAllByUserId(Integer user_id);
}
