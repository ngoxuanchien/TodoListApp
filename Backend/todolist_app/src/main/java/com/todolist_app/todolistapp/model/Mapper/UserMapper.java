package com.todolist_app.todolistapp.model.Mapper;

import com.todolist_app.todolistapp.model.DTO.UserDTO;
import com.todolist_app.todolistapp.model.Entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());

        return user;
    }
}
