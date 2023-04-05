package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.auth.TaskRequest;
import com.todolist_app.todolistapp.model.auth.TaskResponse;
import com.todolist_app.todolistapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskApiController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/all")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping("task/add")
    public ResponseEntity<TaskResponse> addTask(
            @RequestBody TaskRequest taskRequest
    ) {
        return ResponseEntity.ok(taskService.addTask(taskRequest));
    }

    @DeleteMapping("task/delete/{id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PutMapping("task/update/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Integer id,
            @RequestBody TaskRequest taskRequest
    ) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequest));
    }

}
