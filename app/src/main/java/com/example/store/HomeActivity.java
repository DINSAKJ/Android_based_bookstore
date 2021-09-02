package com.example.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    CardView simbtn,uimbtn,pimbtn,cimbtn,fimbtn,mpimbtn,upbtn,profile,orderbtn;
    ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        simbtn = findViewById(R.id.searchbtn);
        cimbtn=findViewById(R.id.complaintbtn);
        fimbtn=findViewById(R.id.feedbackbtn);
        uimbtn = findViewById(R.id.usalebtn);
        pimbtn=findViewById(R.id.pdfbtn);
        upbtn=findViewById(R.id.articlesbtn);
        orderbtn=findViewById(R.id.orderedbtn);
        profile=findViewById(R.id.profilebtn);
        logout=findViewById(R.id.closebtn);

logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new SessionManager(HomeActivity.this).logoutUser();
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }
});



        simbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        uimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,UsedSalesActivity.class);
                startActivity(intent);
            }
        });
       fimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,Feedback.class);
                startActivity(intent);
            }
        });

        pimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PdfList.class);
                startActivity(intent);
            }
        });


        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ArticleActivity.class);
                startActivity(intent);
            }
        });
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,OrderedBook_list.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        cimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,Complaints.class);
                startActivity(intent);
            }
        });

    }
}