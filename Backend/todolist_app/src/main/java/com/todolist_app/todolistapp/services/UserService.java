package com.todolist_app.todolistapp.services;

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

    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {
        int result = 0;
        User user;

        try {
            user = new User(first_name, last_name, email, password);
            userRepository.save(user);
            result = 1;
        } catch (DataIntegrityViolationException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            result = 0;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            result = 0;
        }

        return result;
    }
}
