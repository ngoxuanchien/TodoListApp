package com.example.todo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.Helpers.StringHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    Button _signInBtn;
    EditText _email, _password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        _email = findViewById(R.id.email);
        _password = findViewById(R.id.password);

        _signInBtn = findViewById(R.id.sign_in_btn);

        _signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    authenticatedUser();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private boolean validateEmail() {
        String email = _email.getText().toString();
        boolean result = true;

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
        boolean result = true;

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
        if (validateEmail() || validatePassword()) {
            String url = StringHelper.url + "/api/v1/auth/authenticate";

            RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", _email.getText());
            jsonObject.put("password", _password.getText());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            try {
                                editor.putString("jwtToken", response.getString("token"));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            editor.apply();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.getMessage());
                    Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
            requestQueue.add(jsonObjectRequest);
        } else {
            // do nothing
        }
    }

    public void gotoSignUpAct(View view) {
    }
}