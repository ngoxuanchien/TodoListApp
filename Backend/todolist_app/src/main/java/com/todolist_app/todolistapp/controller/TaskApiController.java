package com.todolist_app.todolistapp.controller;

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

//    @GetMapping("/tasks")
//    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable Integer user_id) {
//        List<TaskDTO> taskDTOs = taskService.getAllTask(user_id);
//
//        if (taskDTOs == null) {
//            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
//    }
//
//    @GetMapping("/task/{task_id}")
//    public ResponseEntity<TaskDTO> getTask(@PathVariable Integer user_id,
//                                        @PathVariable Integer task_id) {
//        TaskDTO taskDTO = taskService.getTask(user_id, task_id);
//
//        if (taskDTO == null) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
//    }
//
//    @PutMapping("/task/{task_id}/update")
//    public ResponseEntity<String> updateTask(@PathVariable Integer task_id,
//                                    @RequestBody TaskDTO taskDTO) {
//
//        if (!taskService.updateTask(task_id, taskDTO)) {
//            return new ResponseEntity<>("update failed", HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>("update stuccess", HttpStatus.OK);
//    }
//
//    @DeleteMapping("task/{task_id}/delete")
//    public ResponseEntity<String> deleteTask(@PathVariable Integer task_id) {
//        if (!taskService.deleteTask(task_id)) {
//            return new ResponseEntity<>("delete failed", HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>("delete success", HttpStatus.OK);
//    }

}
