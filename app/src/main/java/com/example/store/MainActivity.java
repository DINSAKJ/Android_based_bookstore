package com.example.store;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//               Intent intent=new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
               finish();

           }
       },2000);



    }
}