package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PdfDetails extends AppCompatActivity {

    ImageView imageView;

    String pname,plang,pcategory,pcount,pprice,pdf;
    TextView name,lang,cat,count,price;
    Button dbtn;
    String url=Config.BaseURL+"list_pdf.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_details);

        name= findViewById(R.id.pname);
        lang = findViewById(R.id.plang);
        cat= findViewById(R.id.pcat);
        count = findViewById(R.id.pcount);
        price= findViewById(R.id.pprice);

        Intent i = getIntent();
        pname= i.getStringExtra("pname");
        plang = i.getStringExtra("plang");
        pcategory = i.getStringExtra("pcategory");
        pcount = i.getStringExtra("pcount");
        pprice = i.getStringExtra("pprice");
        pdf=i.getStringExtra("pdf");


        name.setText("- " + name);
        lang.setText("- " + lang);
        cat.setText("- Published In :  " + pcategory);

        count.setText("- " + pcount);
        price.setText("- " + price);

        //
        //tvpgno.setText("- Page count : " + pageno);
       // tvprice.setText("- Price : " + price);
       // tvrtamt.setText("- Rental Price : " + rtamt);
        //tvqty.setText("- Quantity : " + qty);

        if (!TextUtils.isEmpty(pdf)) {

            String imageURL = Config.ImageURL + pdf;
            Picasso.get().load(imageURL).into(imageView);
            //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
        }

    }



    }
