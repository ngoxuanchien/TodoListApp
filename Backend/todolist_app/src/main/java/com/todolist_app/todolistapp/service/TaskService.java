package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.Entity.Task;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.model.auth.TaskRequest;
import com.todolist_app.todolistapp.model.auth.TaskResponse;
import com.todolist_app.todolistapp.repository.TaskRepository;
import com.todolist_app.todolistapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskResponse> getTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<Task> tasks = taskRepository.getAllByEmail(email).orElseThrow();

        return tasks.stream()
                .map(TaskResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = userRepository.findByEmail(email);

        var task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .createdTime(taskRequest.getCreatedTime())
                .user(user.orElseThrow())
                .build();
        taskRepository.save(task);

        System.out.println(task.getDescription());

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdTime(task.getCreatedTime())
                .build();
    }

    public String deleteTask(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();

        if (user.getTasks().contains(task)) {
            taskRepository.deleteById(id);
        } else {
            return "Delete failed";
        }
        return "Delete Success";
    }

    public TaskResponse getTask(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();

        if (user.getTasks().contains(task)) {

        } else {
            return null;
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdTime(task.getCreatedTime())
                .build();
    }

    public TaskResponse updateTask(Integer id, TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();

        if (user.getTasks().contains(task)) {
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            taskRepository.save(task);
        } else {
            return null;
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdTime(task.getCreatedTime())
                .build();
    }
}
