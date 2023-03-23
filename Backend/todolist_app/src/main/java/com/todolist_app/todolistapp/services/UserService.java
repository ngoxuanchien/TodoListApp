package com.todolist_app.todolistapp.services;

import com.todolist_app.todolistapp.models.User;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {
//        return userRepository.registerNewUser(first_name, last_name, email, password);
//    }

    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password) {

        try {
            User user = new User();
            user.set

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            return 0;
        }

        return 1;
    }
}
