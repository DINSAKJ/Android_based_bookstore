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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText  editname,editage,editaddress,editmobile,editemail,editusername,editpassword,editconfirmpassword,editotp;
    RadioGroup rgGender;
    RadioButton radioButton1;
    Button btnreg,btnotp;
    String name,age,address,gender,mobile,email,username,password,confirmpassword,otp,stringotp;
    int selectedid;
    String status,error;
    String url=Config.BaseURL+"registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        //getSupportActionBar().setTitle("Registration form");


       editname=findViewById(R.id.name);
       editage=findViewById(R.id.age);
       editaddress=findViewById(R.id.address);
       editmobile=findViewById(R.id.mobile);
       editemail=findViewById(R.id.email);
       editusername=findViewById(R.id.username);
       editpassword=findViewById(R.id.password);
       editconfirmpassword=findViewById(R.id.confirmpass);
       editotp=findViewById(R.id.otp);
       btnreg=findViewById(R.id.regbtn);
       btnotp=findViewById(R.id.otpbtn);
       rgGender = findViewById(R.id.gender);

       btnotp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            checkPermission();
           }
       });

       btnreg.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View view) {
          reg();
      }
      });

        }
        private void reg() {

            name = editname.getText().toString();
            age = editage.getText().toString();
            address = editaddress.getText().toString();
            mobile = editmobile.getText().toString();
            email = editemail.getText().toString();
            username = editusername.getText().toString();
            password = editpassword.getText().toString();
            confirmpassword = editconfirmpassword.getText().toString();
            otp = editotp.getText().toString();

            if (TextUtils.isEmpty(name)) {
                editname.setError("Field value cannot be empty");
                editname.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(age)) {
                editage.setError("Field value cannot be empty");
                editage.requestFocus();
                return;
            }

            selectedid = rgGender.getCheckedRadioButtonId();
            radioButton1 = findViewById(selectedid);
            gender = radioButton1.getText().toString();


            if (TextUtils.isEmpty(address)) {
                editaddress.setError("Field value cannot be empty");
                editaddress.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mobile)) {
                editmobile.setError("Field value cannot be empty");
                editmobile.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                editemail.setError("Field value cannot be empty");
                editemail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(username)) {
                editusername.setError("Field value cannot be empty");
                editusername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                editpassword.setError("Field value cannot be empty");
                editpassword.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(confirmpassword)) {
                editconfirmpassword.setError("Field value cannot be empty");
                editconfirmpassword.requestFocus();
                return;
            }
            if(!password.equals(confirmpassword)){
                editconfirmpassword.setError("Password does not match");
                editconfirmpassword.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(otp)) {
                editotp.setError("Please enter OTP");
                editotp.requestFocus();
                return;

            }if(!otp.equals(stringotp)) {
                editotp.setError("OTP does not match");
                editotp.requestFocus();
                return;
            }

                if (!isPhoneValid(mobile)){
                editmobile.setError("Please enter the correct mobile number");
                editmobile.requestFocus();
                return;
            }
            if (!isEmailValid(email)){
                editemail.setError("Please enter the correct email id");
                editemail.requestFocus();
                return;
            }
            if (password.length()<6){
                editpassword.setError("Please enter the password of minimum 6 number");
                editpassword.requestFocus();
                return;
            }



            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject j = new JSONObject(response);
                        status = j.getString("status");
                        error= j.getString("message");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (status.equals("0")) {
                        Toast.makeText(RegistrationActivity.this, error, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent rs = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(rs);
                        finish();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> M = new HashMap<>();
                    M.put("name", name);
                    M.put("age", age);
                    M.put("gender", gender);
                    M.put("address", address);

                    M.put("mobile", mobile);
                    M.put("email", email);
                    M.put("username", username);
                    M.put("password", password);

                    return M;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }

    public void sendOTP() {
        String num = editmobile.getText().toString();
        if (TextUtils.isEmpty(num)) {
            editmobile.setError("Please enter a number");
            editmobile.requestFocus();
            return;
        }

            Random r = new Random();
        int otp = r.nextInt((9999 - 1000) + 1) + 1000;  //Range: [0,8999]+1000 = [1000,9999]

        String msg = "Welcome to Android based BookStore. Your OTP for Registration is " + otp;

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(num, null, msg, null, null);
        stringotp=Integer.toString(otp);
    }


    // Function to check and request permission.
    public void checkPermission()
    {
        if (ContextCompat.checkSelfPermission(RegistrationActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(RegistrationActivity.this, new String[] { Manifest.permission.SEND_SMS }, 1);
        }
        else {
            sendOTP();

       }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOTP();
            }
            else {
                Toast.makeText(RegistrationActivity.this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static boolean isPhoneValid(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
    
}
