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
import com.example.todo.Helpers.TokenManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    TextView _firstName, _lastName, _email;
    Button _saveBtn;
    TokenManager _tokenManager;
    private final static String url = StringHelper.url + "/api/v1/user/update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        _tokenManager = new TokenManager(EditProfileActivity.this);

        _firstName = findViewById(R.id.first_name);
        _lastName = findViewById(R.id.last_name);
        _email = findViewById(R.id.email);

        _firstName.setText(getIntent().getStringExtra("firstname"));
        _lastName.setText(getIntent().getStringExtra("lastname"));
        _email.setText(getIntent().getStringExtra("email"));

        _saveBtn = findViewById(R.id.saveBtn);

        _saveBtn.setOnClickListener(v -> {
            try {
                SaveProfile();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private boolean ValidateFirstName() {
        boolean result;
        String firstName = _firstName.getText().toString();

        if (firstName.isEmpty()) {
            _firstName.setError("First name cannot empty");
            result = false;
        } else {
            _firstName.setError(null);
            result = true;
        }

        return result;
    }

    public boolean ValidateLastName() {
        boolean result;
        String lastName = _lastName.getText().toString();

        if (lastName.isEmpty()) {
            _lastName.setError("Last name cannot empty");
            result = false;
        } else {
            _lastName.setError(null);
            result = true;
        }

        return result;
    }

    private void SaveProfile() throws JSONException {
        if (ValidateFirstName() && ValidateLastName()) {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + _tokenManager.getToken());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstname", _firstName.getText());
            jsonObject.put("lastname", _lastName.getText());
            jsonObject.put("email", _email.getText());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    url,
                    jsonObject,
                    response -> {
                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    },
                    error -> {
                        System.out.println(error.getMessage());
                        Toast.makeText(EditProfileActivity.this, "Save error", Toast.LENGTH_LONG).show();
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
            requestQueue.add(jsonObjectRequest);
        }

    }

    public void goToProfile(View view) {
        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}