package com.todolist_app.todolistapp;

import com.todolist_app.todolistapp.model.Entity.Task;
import com.todolist_app.todolistapp.model.Entity.User;
import com.todolist_app.todolistapp.repository.TaskRepository;
import com.todolist_app.todolistapp.repository.UserRepository;
import com.todolist_app.todolistapp.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TaskRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;



    @Test
    public void testAddNew() {
        Integer userId = 4;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

//        List<Task> tasks = (List)user.getTasks();
//
//        for (Task task : tasks) {
//            System.out.println(task.getTask());
//        }

        Task task = new Task();
        task.setTask("Do exercise");
        task.setUser(user);

        Task taskSave = taskRepository.save(task);

        Assertions.assertThat(taskSave).isNotNull();
        Assertions.assertThat(taskSave.getId()).isGreaterThan(0);
    }

    @Test
    public  void testFindAllByEmail() {

    }

    @Test
    public void testGetAllByEmail() {
        Integer id = 4;
        User user = userRepository.getById(id);
        List<Task> tasks = taskRepository.getAllByUserId(user.getId());

        for (Task task : tasks) {
            System.out.println(task.getTask());
        }
    }

    @Autowired
    TaskService taskService;



}
