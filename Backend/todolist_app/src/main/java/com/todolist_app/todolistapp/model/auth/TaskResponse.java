package com.todolist_app.todolistapp.model.auth;

import com.todolist_app.todolistapp.model.Entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Integer id;
    private String title;
    private String description;
    private long createdTime;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static TaskResponse convertToResponse(Task task) {
        return modelMapper.map(task, TaskResponse.class);
    }
}
