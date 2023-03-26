package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT t.id, t.task, t.user_id FROM tasks t " +
            "JOIN users u on t.user_id = u.id " +
            "WHERE u.email = :email", nativeQuery = true)
    List<Task> getAllByEmail(String email);


    @Query(value = "SELECT * FROM tasks t WHERE t.user_id = :user_id", nativeQuery = true)
    List<Task> getAllByUserId(Integer user_id);

    @Query(value = "SELECT * FROM tasks t WHERE t.id = :id", nativeQuery = true)
    Task getById(Integer id);
}
