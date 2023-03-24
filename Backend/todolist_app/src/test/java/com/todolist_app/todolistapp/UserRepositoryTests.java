package com.todolist_app.todolistapp;

import com.todolist_app.todolistapp.model.Task;
import com.todolist_app.todolistapp.model.User;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("xuanchien@gmail.com");
        user.setPassword("1233");
        user.setFirst_name("3Chien");
        user.setLast_name("Xuana");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword(("password"));
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("password");
    }

    @Test
    public void getTest() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

    @Test
    public void testFindByEmail() {
        String email = "ngoxuanchien@gmail.com";
        User user = repo.findByEmail(email);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isGreaterThan(0);
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void TestFindAllTaskByEmail() {
        String email = "1234@gmail.com";
        ArrayList<Task> taskList = (ArrayList<Task>)repo.findAllTaskByEmail(email);

        for (Task task: taskList) {
            System.out.println(task.getTask());
        }
    }

}
