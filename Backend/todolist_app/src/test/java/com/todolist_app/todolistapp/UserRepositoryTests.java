package com.todolist_app.todolistapp;

import com.google.gson.Gson;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
    public void test() {
        String jsonString = "{\"id\":\"4\",\"first_name\":\"Chien\",\"last_name\":\"Ngo\",\"email\":\"ngoxuanchien9a@gmail.com\",\"password\":\"$2a$10$M3mPtWFHatZesqXzEU9wtOX0wVGJXae7dKIJf/TbJoDWRskpvFCbu\",\"tasks\":[{\"id\":\"5\",\"task\":\"Do exercise\",\"user_id\":\"5\"}]}";
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);

        System.out.println(user);


    }

}
