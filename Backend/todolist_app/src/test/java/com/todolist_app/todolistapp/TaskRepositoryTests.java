package com.todolist_app.todolistapp;

import com.todolist_app.todolistapp.models.Task;
import com.todolist_app.todolistapp.models.User;
import com.todolist_app.todolistapp.repository.TaskRepository;
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
public class TaskRepositoryTests {

    @Autowired private TaskRepository taskRepository;
    @Autowired private UserRepository userRepository;

    @Test
    public void testAddNew() {
        Integer userId = 2;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        Task task = new Task();
        task.setTask("Do exercise");
        task.setUser(user);

        Task taskSave = taskRepository.save(task);

        Assertions.assertThat(taskSave).isNotNull();
        Assertions.assertThat(taskSave.getId()).isGreaterThan(0);
    }
}
