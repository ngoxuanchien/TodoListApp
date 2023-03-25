package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.Task;
import com.todolist_app.todolistapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/{user_id}")
public class TaskApiController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Integer user_id) {
        List<Task> tasks = null;
        try {
            tasks = taskService.getAllTask(user_id);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Task task : tasks) {
            System.out.println(task);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
