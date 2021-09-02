package com.example.store;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    EditText etusername, etpassword;
    Button logbutton, regbutton;
    String user, pass;
    TextView tv,etfogotpassword;
    String url = Config.BaseURL+"login.php";
    String status,message;
    String id,name,email,address,mobile,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setTitle("login form");
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        logbutton = findViewById(R.id.login_btn);
        regbutton = findViewById(R.id.reg_btn);
       etfogotpassword=findViewById(R.id.fpass);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        etfogotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });




        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
    }

    private void login() {
        user = etusername.getText().toString();
        pass = etpassword.getText().toString();
        if (TextUtils.isEmpty(user)) {
            etusername.setError("Field value cannot be empty");
            etusername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            etpassword.setError("Field value cannot be empty");
            etpassword.requestFocus();
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject j = new JSONObject(response);
                    status = j.getString("status");
                    message = j.getString("message");
                    id=j.getString("id");
                    name=j.getString("name");
                    email=j.getString("email");
                    address=j.getString("address");
                    mobile=j.getString("mobile");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (status.equals("1")) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    new SessionManager(LoginActivity.this).createLoginSession(id,name,email,address,mobile,user);
                    Intent rs = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(rs);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> M = new HashMap<>();
                M.put("username", user);
                M.put("password", pass);
                return M;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

