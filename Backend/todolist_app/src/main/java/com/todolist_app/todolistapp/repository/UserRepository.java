package com.todolist_app.todolistapp.repository;

import com.todolist_app.todolistapp.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO USERS(first name, last name, email, password) VALUES(:first_name, :last_name, :email, :password)", nativeQuery = true)
//    int registerNewUser(@Param("first_name") String first_name,
//                        @Param("last_name") String last_name,
//                        @Param("email") String email,
//                        @Param("password") String password);
}
