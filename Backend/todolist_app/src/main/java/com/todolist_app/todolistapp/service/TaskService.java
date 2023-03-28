package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.DTO.TaskDTO;
import com.todolist_app.todolistapp.model.Entity.Task;
import com.todolist_app.todolistapp.model.Mapper.TaskMapper;
import com.todolist_app.todolistapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getAllTask(Integer user_id) {
        List<Task> tasks = taskRepository.getAllByUserId(user_id);
        List<TaskDTO> taskDTOs = TaskMapper.toTaskDTOs(tasks);

        return taskDTOs;
    }

    public TaskDTO getTask(Integer user_id, Integer task_id) {
        Task task = taskRepository.getById(task_id);
        return TaskMapper.toTaskDTO(task);
    }

    public boolean updateTask(Integer task_id, TaskDTO taskDTO) {
        Task task = taskRepository.getById(task_id);
        task.setTask(taskDTO.getTask());
        taskRepository.save(task);
        return true;
    }

    public boolean deleteTask(Integer task_id) {
        try {
            taskRepository.deleteById(task_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
