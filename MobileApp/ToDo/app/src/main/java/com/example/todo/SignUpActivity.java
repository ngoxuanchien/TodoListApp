package com.example.todo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity {

    EditText _firstName, _lastName, _email, _password, _confirm;
    Button _signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        _firstName = findViewById(R.id.first_name);
        _lastName = findViewById(R.id.last_name);
        _email = findViewById(R.id.email);
        _password = findViewById(R.id.password);
        _confirm = findViewById(R.id.confirm);

        _signUpBtn = findViewById(R.id.sign_up_btn);

        _signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormFields();
            }
        });
    }

    private boolean validatedEmail() {
        return false;
    }

    private void processFormFields() {

    }
}