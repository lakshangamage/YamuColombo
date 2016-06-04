package com.phrixus.yamucolombo.yamucolomboversion10.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.phrixus.yamucolombo.yamucolomboversion10.Controller.AppConfig;
import com.phrixus.yamucolombo.yamucolomboversion10.Controller.ApplicationController;
import com.phrixus.yamucolombo.yamucolomboversion10.Controller.LocalDBController;
import com.phrixus.yamucolombo.yamucolomboversion10.Controller.SessionManager;
import com.phrixus.yamucolombo.yamucolomboversion10.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity {
    private final String CLASS = RegisterActivity.class.getSimpleName();
    private EditText nametxt = null;
    private EditText usernametxt = null;
    private EditText passwordtxt = null;
    private EditText repasswordtxt = null;
    private Button signupbtn = null;
    private ProgressDialog progressDialog = null;
    private SessionManager session;
    private LocalDBController db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nametxt = (EditText) findViewById(R.id.register_nametxt);
        usernametxt = (EditText) findViewById(R.id.register_emailTxt);
        passwordtxt = (EditText) findViewById(R.id.register_passwordtxt);
        repasswordtxt = (EditText) findViewById(R.id.register_repasswordtxt);
        signupbtn = (Button) findViewById(R.id.register_signupbtn);

        //Set progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        // SQLite database handler
        db = new LocalDBController(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametxt.getText().toString().trim();
                String email = usernametxt.getText().toString().trim();
                String password = passwordtxt.getText().toString().trim();
                String repassword = repasswordtxt.getText().toString().trim();
                if(password.equals(repassword)) {
                    if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !repassword.isEmpty()) {
                        registerUser(name, email, password);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Please enter your details!", Toast.LENGTH_LONG)
                                .show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Please reenter the passwords in both places!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
    private void registerUser(final String name, final String username,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        progressDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(CLASS, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // store the user in sqlite
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String username = user.getString("username");

                        // Inserting row in users table
                        db.addUser(username,name);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(CLASS, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
