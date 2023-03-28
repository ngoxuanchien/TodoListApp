package com.todolist_app.todolistapp.model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false, length = 50, name = "first name")
    private String first_name;
    @Column(nullable = false, length = 50, name = "last name")
    private String last_name;

    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    public User() {

    }

    public User(String first_name, String last_name, String email, String password) {
        setFirst_name(first_name);
        setLast_name(last_name);
        setEmail(email);
        setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tasks= " + tasks + '\'' +
                '}';
    }
}
