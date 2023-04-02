package com.todolist_app.todolistapp.service;

import com.todolist_app.todolistapp.model.DTO.UserDTO;
import com.todolist_app.todolistapp.model.Enum.RegisterStatus;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.model.Mapper.UserMapper;
import com.todolist_app.todolistapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public RegisterStatus registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {
        RegisterStatus result;
        User user;

        try {
            user = new User(first_name, last_name, email, password);
            userRepository.save(user);
            result = RegisterStatus.REGISTER_SUCCESS;
        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            result = RegisterStatus.EMAIL_EXISTS;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            result = RegisterStatus.REGISTER_FAILED;
        }

        return result;
    }

//    public UserDTO checkUserEmail(String email) {
//        User user = null;
//        try {
//            user = userRepository.findByEmail(email);
//        } catch (EntityNotFoundException e) {
//            System.out.println();
//            user = null;
//        }
//
//
//        UserDTO userDTO = UserMapper.toUserDTO(user);
//        return userDTO;
//    }
}
