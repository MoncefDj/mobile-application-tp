package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AbstractLogInSignUp {

    private EditText ageEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        PHP = "/signup.php";
        ACTIVITY = "Sign Up";

        setContentView(R.layout.activity_sign_up);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        ageEditText = findViewById(R.id.age);
        addressEditText = findViewById(R.id.address);
        ActivityButton = findViewById(R.id.signup_button);

        ActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Context context = SignUpActivity.this;

                Map<String, String> params = new HashMap<>();
                params.put("email", emailEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                params.put("age", ageEditText.getText().toString());
                params.put("address", addressEditText.getText().toString());

                signUp_logIn(params, context);
            }
        });
    }
}