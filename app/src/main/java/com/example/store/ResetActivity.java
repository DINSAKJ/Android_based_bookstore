package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Random;

public class ResetActivity extends AppCompatActivity {
    EditText etmob,etOTP,etPassword, etConfirmPassword;
    Button btnReset,btnOTP;
    TextView freg;
    ImageButton closebtn;
    String pass, cPass, phone,otpstring,otp2,forgetreg;
    String status, url =Config.BaseURL+"reset_password.php" ;
    String error,stringotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        etmob = findViewById(R.id.mobile);
        etOTP = findViewById(R.id.otp);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnReset = findViewById(R.id.btnReset);
        btnOTP = findViewById(R.id.otpbtn);
        freg=findViewById(R.id.fregister);
        closebtn = findViewById(R.id.closebtn);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpstring = etOTP.getText().toString();
                resetPassword();

            }
        });

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });
        freg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


        private void resetPassword() {
        phone=etmob.getText().toString();
            pass = etPassword.getText().toString();
            cPass = etConfirmPassword.getText().toString();

            if (!pass.equals(cPass)) {
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
                return;
            }


            if (TextUtils.isEmpty(otpstring)) {
                etOTP.setError("Please enter OTP");
                etOTP.requestFocus();
                return;

            }if(!otpstring.equals(otp2)) {
                etOTP.setError("OTP does not match");
                etOTP.requestFocus();
                return;
            }



            //p.setVisibility(View.VISIBLE);
            StringRequest s = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           // p.setVisibility(View.INVISIBLE);
                            try {
                                JSONObject c = new JSONObject(response);
                                status = c.getString("status");
                                error=c.getString("error");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (status.equals("1")) {
                                Toast.makeText(ResetActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ResetActivity.this, "Password cannot change now. Try again later"+error,
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //p.setVisibility(View.INVISIBLE);
                            Toast.makeText(ResetActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("mobile", phone);
                    map.put("password", pass);
                    return map;

                }

            };


            RequestQueue q = Volley.newRequestQueue(this);
            q.add(s);
        }


    public void sendOTP() {

        phone = etmob.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            etmob.setError("hi");
            etmob.requestFocus();
            return;
        }


        Random r = new Random();
        int otp = r.nextInt((9999 - 1000) + 1) + 1000;  //Range: [0,8999]+1000 = [1000,9999]

        String msg = "Welcome to Android based Book store. Your OTP for changing the password is " + otp;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, msg, null, null);
        otp2 = String.valueOf(otp);

    }
    // Function to check and request permission.
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(ResetActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(ResetActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            sendOTP();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOTP();
            } else {
                Toast.makeText(ResetActivity.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




