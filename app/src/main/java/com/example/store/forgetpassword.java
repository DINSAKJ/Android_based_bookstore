package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

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
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgetpassword extends AppCompatActivity {
    EditText mobile, otp;
    String otpS, number;
    Button sendotp, verifybtn;
    ImageButton closebtn;
    ProgressBar p;
    String phone, status = "", url = Config.BaseURL + "check_phone.php";
String stringotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        mobile = findViewById(R.id.mobile);
        otp = findViewById(R.id.otp);
        verifybtn = findViewById(R.id.verifybtn);
        sendotp = findViewById(R.id.otpbtn);
        closebtn = findViewById(R.id.closebtn);
        p = findViewById(R.id.progress);


        // Close This page
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpassword.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });
        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        phone = mobile.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            mobile.setError("Phone required");
            return;
        } else if (!isPhoneValid(phone)) {
            mobile.setError("Invalid phone number");
            return;
        }





        if(!otp.equals(stringotp)) {
            otp.setError("OTP does not match");
            otp.requestFocus();
            return;
        }
        p.setVisibility(View.VISIBLE);
        StringRequest s = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        p.setVisibility(View.INVISIBLE);
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("StatusID");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (status.equals("1")) {
                            checkPermission();
                        } else {
                            Toast.makeText(forgetpassword.this, "Mobile number is not registered",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p.setVisibility(View.INVISIBLE);
                        Toast.makeText(forgetpassword.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("phone", phone);
                return map;
            }
        };
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(s);
    }


    public void sendOTP() {

        number = mobile.getText().toString();

        if(TextUtils.isEmpty(number)){
            mobile.setError("hi");
            mobile.requestFocus();
            return;
        }








        Random r = new Random();
        int otp = r.nextInt((9999 - 1000) + 1) + 1000;  //Range: [0,8999]+1000 = [1000,9999]

        String msg = "Welcome to Coaching Hub. Your OTP for changing the password is " + otp;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, msg, null, null);

        Intent i = new Intent(forgetpassword.this, ResetActivity.class);
        i.putExtra("otp", Integer.toString(otp));
        i.putExtra("phone", phone);
        startActivity(i);
        finish();
    }


    // Function to check and request permission.
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(forgetpassword.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(forgetpassword.this, new String[]{Manifest.permission.SEND_SMS}, 1);
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
                Toast.makeText(forgetpassword.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private boolean isPhoneValid(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
   }
    }
