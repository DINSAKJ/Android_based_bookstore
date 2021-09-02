package com.example.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UsedSalesActivity extends AppCompatActivity {
    CardView searchbtn,salebtn,mysalebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_sales);

        salebtn=findViewById(R.id.uib1);
        searchbtn=findViewById(R.id.uib2);
        mysalebtn=findViewById(R.id.uib3);

        salebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsedSalesActivity.this, Sale_BookActivity.class);
                startActivity(intent);
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsedSalesActivity.this, UsedListActivity.class);
                startActivity(intent);
            }
        });

       mysalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsedSalesActivity.this, ListMySalesActivity.class);
                startActivity(intent);
            }
        });



    }
}