package com.todolist_app.todolistapp.model.auth;

import com.todolist_app.todolistapp.model.Entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.function.Function;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Integer id;
    private String task;
    private String status;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static TaskResponse convertToResponse(Task task) {
        return modelMapper.map(task, TaskResponse.class);
    }
}
