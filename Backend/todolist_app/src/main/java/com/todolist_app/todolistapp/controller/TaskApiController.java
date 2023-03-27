package com.todolist_app.todolistapp.controller;

import com.todolist_app.todolistapp.model.Entity.Task;
import com.todolist_app.todolistapp.model.DTO.TaskDTO;
import com.todolist_app.todolistapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/task/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer user_id,
                                        @PathVariable Integer task_id) {
        Task task = null;

        try {
            task = taskService.getTask(user_id, task_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/task/{task_id}")
    public ResponseEntity<String> getTask(@PathVariable Integer user_id,
                                    @PathVariable Integer task_id,
                                    @RequestBody TaskDTO taskDTO) {

        taskService.updateTask(task_id, taskDTO);

        return new ResponseEntity<>("update stuccess", HttpStatus.OK);
    }

}
