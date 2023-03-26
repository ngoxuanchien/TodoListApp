package com.todolist_app.todolistapp.model.DTO;

import lombok.Data;

@Data
public class TaskDTO {
    private Integer id;
    private String task;
    private Integer user_id;
}
