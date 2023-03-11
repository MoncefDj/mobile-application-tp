package com.example.myapplication;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public abstract class HTTP extends AppCompatActivity {

    protected final String ADRESS = "192.168.1.106";
    protected String PHP;
    protected String ACTIVITY;
    protected Button ActivityButton;
    protected EditText emailEditText;
    protected EditText passwordEditText;

    protected void signUp_logIn(Map<String, String> params, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method. POST, "http://"+ADRESS+PHP,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        responseRecieved(response, context, params);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error) {
                        Toast.makeText (context, "Error:" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                return params;
            }
        };
        // Add request to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    protected abstract void responseRecieved(String response, Context context, Map<String, String> params);
}
