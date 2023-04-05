package com.example.todo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.Helpers.StringHelper;
import com.example.todo.Helpers.TokenManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    EditText _title, _description;
    Button _addBtn;

    String url = StringHelper.url + "/api/v1/task/add";

    TokenManager _tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        _tokenManager = new TokenManager(AddTaskActivity.this);

        _title = findViewById(R.id.title);
        _description = findViewById(R.id.description);

        _addBtn = findViewById(R.id.add_task);
        _addBtn.setOnClickListener(view -> {
            try {
                AddNewTask();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private boolean ValidateTitle() {
        boolean result;

        String title = _title.getText().toString();
        if (title.isEmpty()) {
            _title.setError("Title cannot be empty");
            result = false;
        } else {
            _title.setError(null);
            result = true;
        }

        return result;
    }

    private void AddNewTask() throws JSONException {
        if (ValidateTitle()) {
            String title = _title.getText().toString();
            String description = _description.getText().toString();
            long createdTime = System.currentTimeMillis();

            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + _tokenManager.getToken());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("createdTime", createdTime);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    response -> {
                        Intent intent = new Intent(AddTaskActivity.this, TaskActivity.class);
                        startActivity(intent);
                        finish();
                    },
                    error -> {
                        System.out.println(error.getMessage());
                        Toast.makeText(AddTaskActivity.this, "Add failed", Toast.LENGTH_LONG).show();
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddTaskActivity.this);
            requestQueue.add(jsonObjectRequest);
        }
    }

    public void goToTaskAct(View view) {
        Intent intent = new Intent(AddTaskActivity.this, TaskActivity.class);
        startActivity(intent);
        finish();
    }
}