package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends HTTP {

    private EditText ageEditText;
    private EditText addressEditText;
    private EditText first_nameEditText;
    private EditText family_nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        PHP = "/signup.php";
        ACTIVITY = "Sign Up";

        setContentView(R.layout.activity_sign_up);

        first_nameEditText = findViewById(R.id.first_name);
        family_nameEditText = findViewById(R.id.family_name);
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
                params.put("first_name", first_nameEditText.getText().toString());
                params.put("family_name", family_nameEditText.getText().toString());
                params.put("email", emailEditText.getText().toString());
                params.put("password", passwordEditText.getText().toString());
                params.put("age", ageEditText.getText().toString());
                params.put("address", addressEditText.getText().toString());

                signUp_logIn(params, context);
            }
        });
    }

    @Override
    protected void responseRecieved(String response, Context context, Map<String, String> params) {
        try {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String status = jsonObject.getString("status");
            if (status.equals("success")) {
                JSONObject userObject = jsonObject.getJSONObject("user_info");
                String family_name = userObject.getString("family_name");
                String first_name = userObject.getString("first_name");
                String email = userObject.getString("email");
                int age = userObject.getInt("age");
                String address = userObject.getString("address");

                // Save user information to shared preferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("family_name", family_name);
                editor.putString("first_name", first_name);
                editor.putString("email", email);
                editor.putInt("age", age);
                editor.putString("address", address);
                editor.apply();

                // Start HomeActivity
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                String errorMessage = jsonObject.getString("message");
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}