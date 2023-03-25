package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.Task;
import com.todolist_app.todolistapp.model.User;
import com.todolist_app.todolistapp.repository.TaskRepository;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTask(Integer user_id) {
        List<Task> result = null;
        System.out.println(user_id);

        try {
            result = taskRepository.getAllByUserId(user_id);
        } catch (DataIntegrityViolationException e) {
            result = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
