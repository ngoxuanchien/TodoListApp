package com.todolist_app.todolistapp.services;

import com.todolist_app.todolistapp.Enum.Status;
import com.todolist_app.todolistapp.models.User;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {
//        return userRepository.registerNewUser(first_name, last_name, email, password);
//    }

    public Status registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {
        Status result;
        User user;

        try {
            user = new User(first_name, last_name, email, password);
            userRepository.save(user);
            result = Status.REGISTER_SUCCESS;
        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            result = Status.EMAIL_EXISTS;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            result = Status.REGISTER_FAILED;
        }

        return result;
    }
}
