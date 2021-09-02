package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class ProfileActivity extends AppCompatActivity {
    String name,email, address, mobile, id, status, message;
    EditText tvproname,tvproemail, tvproaddress, tvpromobile;
    Button changebtn;
    String url = Config.BaseURL + "profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvproname = findViewById(R.id.pname);
        tvproemail = findViewById(R.id.pemail);
        tvproaddress = findViewById(R.id.paddress);
        tvpromobile = findViewById(R.id.pmob);
        changebtn = findViewById(R.id.changeprobtn);

        name = new SessionManager(this).getUserDetails().get("name");
        email = new SessionManager(this).getUserDetails().get("email");
        address = new SessionManager(this).getUserDetails().get("address");
        mobile = new SessionManager(this).getUserDetails().get("mobile");
        tvproname.setText(name);
        tvproemail.setText(email);
        tvproaddress.setText(address);
        tvpromobile.setText(mobile);


        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg();
            }
        });

    }








     //   HashMap<String, String> h = new HashMap<>();
      //  name = h.get("name");
      //  email = h.get("email");
      //  address = h.get("address");
      //  mobile = h.get("mobile");
      //  id = h.get("id");

       // tvproname.setText(name);
        //tvproemail.setText(email);
       // tvproaddress.setText(address);
      //  tvpromobile.setText(mobile);

   // }

    private void reg() {

        name = tvproname.getText().toString();
        email= tvproemail.getText().toString();
        address = tvproaddress.getText().toString();
        mobile = tvpromobile.getText().toString();



        if (TextUtils.isEmpty(name)) {
            tvproname.setError("Field value cannot be empty");
            tvproname.requestFocus();
            return;
        }

        //Toast.makeText(ProfileActivity.this, "1", Toast.LENGTH_SHORT).show();

        if (TextUtils.isEmpty(email)) {
            tvproemail.setError("Field value cannot be empty");
            tvproemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            tvproaddress.setError("Field value cannot be empty");
            tvproaddress.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            tvpromobile.setError("Field value cannot be empty");
            tvpromobile.requestFocus();
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

                if (status.equals("0")) {
                    Toast.makeText(ProfileActivity.this, "Profile updation failed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ProfileActivity.this, "Profile updation successs", Toast.LENGTH_SHORT).show();
                    //Intent rs = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
                    finish();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> M = new HashMap<>();
                M.put("name", name);
                 M.put("email", email);
               // M.put("age", age);
                M.put("address", address);
               // M.put("gender", gender);
                M.put("mobile", mobile);

               // M.put("username", username);
               // M.put("password", password);
               // M.put("confirmpassword", confirmpassword);
                return M;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}


