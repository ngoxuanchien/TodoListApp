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

    /**
     * Get user's list tasks
     * @return List TaskResponse
     */
    public List<TaskResponse> getTasks() {
        // Get user emaill form authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Get List<Task> form user email
        List<Task> tasks = taskRepository.getAllByEmail(email).orElseThrow();

        return tasks.stream()
                .map(TaskResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Add a task to user list tasks
     * @param taskRequest
     * @return TaskResponse
     */
    public TaskResponse addTask(TaskRequest taskRequest) {
        // Get user form authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = userRepository.findByEmail(email);

        // Build new task
        var task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .createdTime(taskRequest.getCreatedTime())
                .user(user.orElseThrow())
                .build();

        // Save new task
        taskRepository.save(task);

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .createdTime(task.getCreatedTime())
                .build();
    }

    /**
     * Delete Task form task id
     * @param id
     * @return "Delete failed" / "Delete Success"
     */
    public String deleteTask(Integer id) {

        // Get user form authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // Get task form id
        Task task = taskRepository.findById(id).orElseThrow();

        // Check if the task belong user
        if (user.getTasks().contains(task)) {
            taskRepository.deleteById(id);
        } else {
            return "Delete failed";
        }
        return "Delete Success";
    }

    /**
     * Get task form id
     * @param id
     * @return TaskResponse
     */
    public TaskResponse getTask(Integer id) {
        // Get user form authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // Get task form id
        Task task = taskRepository.findById(id).orElseThrow();

        // Check if the task belong user
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

    /**
     * Update task
     * @param id
     * @param taskRequest
     * @return TaskResponse
     */
    public TaskResponse updateTask(Integer id, TaskRequest taskRequest) {
        // Get user form authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        // Get task form id
        Task task = taskRepository.findById(id).orElseThrow();

        // Update task if the task belong user
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
