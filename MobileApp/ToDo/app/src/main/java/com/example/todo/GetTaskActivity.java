package com.example.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todo.Helpers.Task;
import com.example.todo.Helpers.TokenManager;

public class GetTaskActivity extends AppCompatActivity {

    TokenManager _tokenManager;

    Task _task;

    TextView _title, _description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_task);

        _tokenManager = new TokenManager(GetTaskActivity.this);

        _title = findViewById(R.id.title);
        _description = findViewById(R.id.description);

        _task = Task.builder()
                .id(getIntent().getIntExtra("id", -1))
                .title(getIntent().getStringExtra("title"))
                .description(getIntent().getStringExtra("description"))
                .timeCreated(getIntent().getLongExtra("timeCreated", 0))
                .build();

        _title.setText(_task.getTitle());
        _description.setText(_task.getDescription());
    }

    public void goToDeleteAct(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GetTaskActivity.this);
        builder.setTitle("Delete task");
        builder.setMessage("Are you sure to delete this task?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            Intent intent = new Intent(GetTaskActivity.this, DeleteTaskActivity.class);
            intent.putExtra("id", _task.getId());
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void goToEditAct(View view) {
        Intent intent = new Intent(GetTaskActivity.this, EditTaskActivity.class);
        intent.putExtra("id", _task.getId());
        intent.putExtra("title", _task.getTitle());
        intent.putExtra("description", _task.getDescription());
        intent.putExtra("timeCreated", _task.getTimeCreated());
        startActivity(intent);
        finish();
    }

    public void goToTaskAct(View view) {
        Intent intent = new Intent(GetTaskActivity.this, TaskActivity.class);
        startActivity(intent);
        finish();
    }
}