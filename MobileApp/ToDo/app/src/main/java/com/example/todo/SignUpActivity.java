package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SignUpActivity extends AppCompatActivity {

    EditText _firstName, _lastName, _email, _password, _confirm;
    Button _signUpBtn;
    final String url = StringHelper.url + "/api/v1/auth/register";

    TokenManager _tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        _tokenManager = new TokenManager(SignUpActivity.this);

        _firstName = findViewById(R.id.first_name);
        _lastName = findViewById(R.id.last_name);
        _email = findViewById(R.id.email);
        _password = findViewById(R.id.password);
        _confirm = findViewById(R.id.confirm);

        _signUpBtn = findViewById(R.id.sign_up_btn);

        _signUpBtn.setOnClickListener(view -> {
            try {
                processFormFields();
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

    private boolean validateFirstName() {
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

    public boolean validateLastName() {
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

    public boolean validatePasswordAndConfirm() {
        boolean result;
        String password = _password.getText().toString();
        String confirm = _confirm.getText().toString();

        if (password.isEmpty()) {
            _password.setError("Password cannot be empty");
            result = false;
        } else if (!password.equals(confirm)) {
            _password.setError("Password do not match!");
            result = false;
        } else {
            _password.setError(null);
            _confirm.setError(null);
            result = true;
        }

        return result;
    }

    private void processFormFields() throws JSONException {

        if (validateFirstName() && validateLastName() && validateEmail() && validatePasswordAndConfirm()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstname", _firstName.getText());
            jsonObject.put("lastname", _lastName.getText());
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
                    }, error -> {
                        System.out.println(error.getMessage());
                        Toast.makeText(SignUpActivity.this, "Register error", Toast.LENGTH_LONG).show();
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            requestQueue.add(jsonObjectRequest);
        }  // do nothing;

    }

    public void goToSignInAct(View view) {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToHome(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}