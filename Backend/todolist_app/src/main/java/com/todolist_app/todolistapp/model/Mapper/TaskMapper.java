package com.todolist_app.todolistapp.model.Mapper;

import com.todolist_app.todolistapp.model.DTO.TaskDTO;
import com.todolist_app.todolistapp.model.Entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTask(task.getTask());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    public static List<TaskDTO> toTaskDTOs(List<Task> tasks) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOs.add(toTaskDTO(task));
        }

        return taskDTOs;
    }

    public static Task toTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTask(taskDTO.getTask());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
