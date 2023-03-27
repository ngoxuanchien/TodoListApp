package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.DTO.TaskDTO;
import com.todolist_app.todolistapp.model.Entity.Task;
import com.todolist_app.todolistapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask(Integer user_id) {
//        System.out.println(taskRepository);
        List<Task> result = taskRepository.getAllByUserId(user_id);

        return result;
    }

    public Task getTask(Integer user_id, Integer task_id) {
        Task task = taskRepository.getById(task_id);
        return task;
    }

    public boolean updateTask(Integer task_id, TaskDTO taskDTO) {
        Task task = taskRepository.getById(task_id);
        task.setTask(taskDTO.getTask());
        taskRepository.save(task);
        return true;
    }
}
