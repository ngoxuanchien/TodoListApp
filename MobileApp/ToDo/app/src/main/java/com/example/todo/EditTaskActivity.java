package com.example.todo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.Helpers.StringHelper;
import com.example.todo.Helpers.Task;
import com.example.todo.Helpers.TokenManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditTaskActivity extends AppCompatActivity {

    String url = StringHelper.url + "/api/v1/task/update/";
    Task _task;
    TokenManager _tokenManager;

    TextView _title, _description;

    Button _saveBtn;

    private void SaveTask() throws JSONException {
        url = url + _task.getId();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + _tokenManager.getToken());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", _title.getText());
        jsonObject.put("description", _description.getText());
        jsonObject.put("createdTime", _task.getTimeCreated());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                jsonObject,
                response -> {
                    Intent intent = new Intent(EditTaskActivity.this, TaskActivity.class);
                    startActivity(intent);
                    Toast.makeText(EditTaskActivity.this, "Update success", Toast.LENGTH_LONG).show();
                    finish();
                },
                error -> {
                    System.out.println(error.getMessage());
                    Toast.makeText(EditTaskActivity.this, "Has an error. Please try again", Toast.LENGTH_LONG).show();
                }

        ) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditTaskActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        _tokenManager = new TokenManager(EditTaskActivity.this);

        _title = findViewById(R.id.title);
        _description = findViewById(R.id.description);
        _saveBtn = findViewById(R.id.save_task);

        _task = Task.builder()
                .id(getIntent().getIntExtra("id", -1))
                .title(getIntent().getStringExtra("title"))
                .description(getIntent().getStringExtra("description"))
                .timeCreated(getIntent().getLongExtra("timeCreated", 0))
                .build();

        _title.setText(_task.getTitle());
        _description.setText(_task.getDescription());

        _saveBtn.setOnClickListener(view -> {
            try {
                SaveTask();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void goToTaskAct(View view) {
        Intent intent = new Intent(EditTaskActivity.this, TaskActivity.class);
        startActivity(intent);
        finish();
    }
}