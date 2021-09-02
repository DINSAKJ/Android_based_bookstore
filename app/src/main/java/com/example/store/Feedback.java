package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Feedback extends AppCompatActivity {




    EditText feedback;
   Button sendfeedback;
   String fedit,userid;
    String url=Config.BaseURL+"feedback.php";
    String status,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        userid = new SessionManager(this).getUserDetails().get("id");
        feedback = findViewById(R.id.fedit);
        sendfeedback = findViewById(R.id.fbtn);
        sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send();
            }
        });
    }
        private void Send()

    {

            fedit = feedback.getText().toString();
            if (TextUtils.isEmpty(fedit)) {
                feedback.setError("Field value cannot be empty");
                feedback.requestFocus();
                return;
            }

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject j = new JSONObject(response);
                        status = j.getString("status");
                        message = j.getString("message");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (status.equals("1")) {
                        Toast.makeText(Feedback.this, message, Toast.LENGTH_SHORT).show();
                        Intent rs = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(rs);
                    } else {
                        Toast.makeText(Feedback.this, "Failed", Toast.LENGTH_SHORT).show();

                    }


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Feedback.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> M = new HashMap<>();
                    M.put("feedback", fedit);
                    M.put("userid",userid);
                    return M;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);

        }

    }

