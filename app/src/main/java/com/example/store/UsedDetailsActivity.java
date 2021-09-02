package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UsedDetailsActivity extends AppCompatActivity {

    ImageView imageView;

    Button button;

    String usid,ustitle,usauthor,uspublished,usdesc,uslang,usactprice,ussprice,uscontactno,img;
    TextView tvustitle,tvusauthor,tvuspublished,tvusdesc,tvuslang,tvusactprice,tvussellprice,tvuscontactno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_details);
        imageView = findViewById(R.id.imgv);

        tvustitle= findViewById(R.id.ustitle);
        tvusauthor= findViewById(R.id.usauthor);
        tvuspublished= findViewById(R.id.uspublished);
        tvusdesc= findViewById(R.id.usdesc);
        tvuslang = findViewById(R.id.uslang);
        tvusactprice= findViewById(R.id.usap);
        tvussellprice= findViewById(R.id.ussp);
        tvuscontactno= findViewById(R.id.uscn);
        button=findViewById(R.id.callbtn);
        //tvusemail= findViewById(R.id.usemail);

        Intent i = getIntent();
        usid = i.getStringExtra("id");
        ustitle = i.getStringExtra("title");
        usauthor = i.getStringExtra("author");
        uspublished = i.getStringExtra("published");
        usdesc = i.getStringExtra("description");
        uslang = i.getStringExtra("lang");
        usactprice = i.getStringExtra("aprice");
       ussprice = i.getStringExtra("sprice");
        uscontactno= i.getStringExtra("cno");
        //usemail = i.getStringExtra("cmail");
        img = i.getStringExtra("img");


        //tvid.setText("- " + id);
        tvustitle.setText("- " + ustitle);
        tvusauthor.setText("- " + usauthor);
        tvuspublished.setText("- Published In : " + uspublished);
        tvusdesc.setText("- " + usdesc);
        tvuslang.setText("- " + uslang);
        tvusactprice.setText("- Actual Price : " + usactprice);
        tvussellprice.setText("- Selling Price : " + ussprice);
        tvuscontactno.setText("- Contact number : " + uscontactno);
       // tvusemail.setText("- Contact Mail-ID : " + usemail);
        if (!TextUtils.isEmpty(img)) {

            String imageURL = Config.ImageURL + img;
            Picasso.get().load(imageURL).into(imageView);
            //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:"+uscontactno));
                startActivity(dialIntent);

            }
        });

    }

}






