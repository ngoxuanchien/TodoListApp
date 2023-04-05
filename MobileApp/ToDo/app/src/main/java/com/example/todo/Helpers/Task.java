package com.example.todo.Helpers;

import android.content.Intent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    int id;
    String title;
    String description;
    long timeCreated;
}
