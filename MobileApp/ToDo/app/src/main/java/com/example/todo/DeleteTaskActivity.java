package com.example.todo;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.Helpers.StringHelper;
import com.example.todo.Helpers.TokenManager;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteTaskActivity extends AppCompatActivity {

    private String url = StringHelper.url + "/api/v1/task/delete/";
    TokenManager _tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);

        _tokenManager = new TokenManager(DeleteTaskActivity.this);

        url = url + getIntent().getIntExtra("id", -1);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + _tokenManager.getToken());
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                url,
                response -> {
                    Intent intent = new Intent(DeleteTaskActivity.this, TaskActivity.class);
                    startActivity(intent);
                    Toast.makeText(DeleteTaskActivity.this, "Delete success", Toast.LENGTH_LONG).show();
                    finish();
                },
                error -> {
                    System.out.println(error.getMessage());
                    Intent intent = new Intent(DeleteTaskActivity.this, TaskActivity.class);
                    startActivity(intent);
                    Toast.makeText(DeleteTaskActivity.this, "Has an error. Please try again", Toast.LENGTH_LONG).show();
                    finish();
                }

        ) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DeleteTaskActivity.this);
        requestQueue.add(stringRequest);

    }
}