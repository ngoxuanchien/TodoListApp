package com.example.todo;

import android.annotation.SuppressLint;
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
import com.example.todo.Helpers.TokenManager;
import com.example.todo.Helpers.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    String url = StringHelper.url + "/api/v1/user/profile";
    TokenManager _tokenManager;

    TextView _firstName, _lastName, _email;
    Button _editBtn;
    User _user;

    private User ParseJsonResponse(String response) throws JSONException {
        User user;
        JSONObject jsonObject = new JSONObject(response);
        user = User.builder()
                .firstName(jsonObject.getString("firstname"))
                .lastName(jsonObject.getString("lastname"))
                .email(jsonObject.getString("email"))
                .build();

        return user;
    }

    private void RequestProfile() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + _tokenManager.getToken());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        _user = ParseJsonResponse(String.valueOf(response));
                        _firstName.setText(_user.getFirstName());
                        _lastName.setText(_user.getLastName());
                        _email.setText(_user.getEmail());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    System.out.println(error.getMessage());
                    Toast.makeText(ProfileActivity.this, "Has an error. Please try again", Toast.LENGTH_LONG).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _tokenManager = new TokenManager(ProfileActivity.this);

        _firstName = findViewById(R.id.first_name);
        _lastName = findViewById(R.id.last_name);
        _email = findViewById(R.id.email);

        _editBtn = findViewById(R.id.editBtn);

        RequestProfile();
        _editBtn.setOnClickListener(v -> {
            EditProfile();
        });
    }

    private void EditProfile() {
        if (_user != null) {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("firstname", _user.getFirstName());
            intent.putExtra("lastname", _user.getLastName());
            intent.putExtra("email", _user.getEmail());

            startActivity(intent);
            finish();
        }
    }

    public void goToTaskAct(View view) {
        Intent intent = new Intent(ProfileActivity.this, TaskActivity.class);
        startActivity(intent);
        finish();
    }
}