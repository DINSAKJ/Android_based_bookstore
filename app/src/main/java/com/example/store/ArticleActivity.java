package com.example.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ArticleActivity extends AppCompatActivity {

    CardView searchart,uploadart,myarticles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        searchart=findViewById(R.id.up1);
        uploadart=findViewById(R.id.up2);



        searchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this,Article_list.class);
                startActivity(intent);
            }
        });

        uploadart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this,ArticleUploadActivity.class);
                startActivity(intent);
            }
        });


    }
}