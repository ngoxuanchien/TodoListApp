package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
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

public class SignInActivity extends AppCompatActivity {

    Button _signInBtn;
    EditText _email, _password;
    final String url = StringHelper.url + "/api/v1/auth/authenticate";
    TokenManager _tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        _tokenManager = new TokenManager(SignInActivity.this);

        _email = findViewById(R.id.email);
        _password = findViewById(R.id.password);

        _signInBtn = findViewById(R.id.sign_in_btn);

        _signInBtn.setOnClickListener(view -> {
            try {
                authenticatedUser();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private boolean validateEmail() {
        String email = _email.getText().toString();
        boolean result;

        if (email.isEmpty()) {
            _email.setError("Email cannot empty!");
            result = false;
        } else if (!StringHelper.regexEmailValidationPattern(email)) {
            _email.setError("Please enter a valid email!");
            result = false;
        } else {
            _email.setError(null);
            result = true;
        }

        return result;
    }

    private boolean validatePassword() {
        String password = _password.getText().toString();
        boolean result;

        if (password.isEmpty()) {
            _password.setError("Password cannot be empty!");
            result = false;
        } else {
            _password.setError(null);
            result = true;
        }

        return result;
    }

    private void authenticatedUser() throws JSONException {
        if (validateEmail() && validatePassword()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", _email.getText());
            jsonObject.put("password", _password.getText());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    response -> {

                        try {
                            _tokenManager.saveToken(response.getString("token"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        goToTaskAct();
                    }, error -> {
                        System.out.println(error.getMessage());
                        Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
            requestQueue.add(jsonObjectRequest);
        }  // do nothing

    }

    private void goToTaskAct() {
        Intent intent = new Intent(SignInActivity.this, TaskActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoSignUpAct(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToHome(View view) {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}