package com.todolist_app.todolistapp.model.DTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
