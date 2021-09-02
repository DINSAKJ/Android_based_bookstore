package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paytm.pgsdk.Log;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView imageView;

    String userid,id,title,author,published,description,category,lang,img,pageno,price,rtamt,qty,date,mobile,fine;
     TextView tvid,tvtitle,tvauthor,tvpublished,tvdesc,tvcategory,tvlang,tvpgno,tvprice,tvrtamt,tvqty;
     Button bbtn,rbtn;
 String amount, note="test";
 String upivirtualid="sameenakn2000@okicici", name = "xyz";
     String url=Config.BaseURL+"book_sales.php";
     String renturl=Config.BaseURL+"rent_books.php";
     String status,message;
 final int UPI_PAYMENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_bookdetails);
     userid=new SessionManager(this).getUserDetails().get("id");
     mobile=new SessionManager(this).getUserDetails().get("mobile");
     mobile=new SessionManager(this).getUserDetails().get("mobile");
     imageView = findViewById(R.id.imgv);
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     //SimpleDateFormat ddf = new SimpleDateFormat("dd/MM/yyyy");
     date= df.format(new Date());
//date1=ddf.format(new Date());
     tvid = findViewById(R.id.bdid);
     tvtitle = findViewById(R.id.bdtitle);
     tvauthor = findViewById(R.id.bdauthor);
     tvpublished = findViewById(R.id.bdpublished);

     tvdesc = findViewById(R.id.bddesc);
     tvcategory = findViewById(R.id.bdcategory);
     tvlang = findViewById(R.id.bdlang);
     tvpgno = findViewById(R.id.bdpgno);
     tvprice = findViewById(R.id.bdprice);
     tvrtamt = findViewById(R.id.bdrtamt);
     tvqty = findViewById(R.id.bdqty);
     bbtn = findViewById(R.id.buybtn);
     rbtn = findViewById(R.id.rentbtn);
     bbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        //payUsingUpi(name,upivirtualid,note,amount);

       // message();
       buy();
      }
     });
     rbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       rent();
      }
     });



     Intent i = getIntent();
     id = i.getStringExtra("id");
     title = i.getStringExtra("title");
     author = i.getStringExtra("author");
     published = i.getStringExtra("published");
     description = i.getStringExtra("description");
     category = i.getStringExtra("category");
     lang = i.getStringExtra("language");
     img= i.getStringExtra("img");
     pageno = i.getStringExtra("page no");
     price = i.getStringExtra("price");
     rtamt = i.getStringExtra("rtamt");
     qty = i.getStringExtra("qty");

     tvid.setText("- " + id);
     tvtitle.setText("- " + title);
     tvauthor.setText("- " + author);
     tvpublished.setText("- Published In :  " + published);

     tvdesc.setText("- " + description);
     tvcategory.setText("- " + category);
     tvlang.setText("- " + lang);
     //
     tvpgno.setText("- Page count : " + pageno);
     tvprice.setText("- Price : " + price);
     tvrtamt.setText("- Rental Price : " + rtamt);
     tvqty.setText("- Quantity : " + qty);

     if (!TextUtils.isEmpty(img)) {

      String imageURL = Config.ImageURL + img;
      Picasso.get().load(imageURL).into(imageView);
      //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
     }

    }











 private void buy() {


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

    if (status.equals("1")) {
     Toast.makeText(BookDetailsActivity.this, "ordered successfully", Toast.LENGTH_SHORT).show();
     message();
     //Intent rs = new Intent(getApplicationContext(), LoginActivity.class);
    // startActivity(rs);
    } else {
     Toast.makeText(BookDetailsActivity.this, "Failed to order book", Toast.LENGTH_SHORT).show();

    }


   }
  }, new Response.ErrorListener() {
   @Override
   public void onErrorResponse(VolleyError error) {
    Toast.makeText(BookDetailsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
   }
  }) {
   @Override
   protected Map<String, String> getParams() throws AuthFailureError {
    Map<String, String> M = new HashMap<>();
    M.put("bookid", id);
    M.put("userid", userid);
    M.put("price", price);
    M.put("date", date);

    return M;
   }
  };
  RequestQueue requestQueue = Volley.newRequestQueue(this);
  requestQueue.add(request);

 }

 public void message() {

    // Toast.makeText(BookDetailsActivity.this, "1", Toast.LENGTH_SHORT).show();
  String msg = "your order has been successfully placed,Purchase your ordered book from the store with in 5 days from now...otherwise your order will be cancelled" ;

  SmsManager sms = SmsManager.getDefault();
  sms.sendTextMessage(mobile, null, msg, null, null);

 }

 private void rent() {

  StringRequest request = new StringRequest(Request.Method.POST, renturl, new Response.Listener<String>() {
   @Override
   public void onResponse(String response) {
    try {
     JSONObject j = new JSONObject(response);
     status = j.getString("status");
     message = j.getString("message");


    } catch (JSONException e) {
     e.printStackTrace();
    }

    if (status.equals("1")) {
     Toast.makeText(BookDetailsActivity.this, "successfully ordered book for rental", Toast.LENGTH_SHORT).show();
     //Toast.makeText(BookDetailsActivity.this, "2", Toast.LENGTH_SHORT).show();
      mess();

     //Intent rs = new Intent(getApplicationContext(), LoginActivity.class);
     // startActivity(rs);
    } else {
     Toast.makeText(BookDetailsActivity.this, "Failed to order book for rental", Toast.LENGTH_SHORT).show();

    }


   }
  }, new Response.ErrorListener() {
   @Override
   public void onErrorResponse(VolleyError error) {
    Toast.makeText(BookDetailsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
   }
  }) {
   @Override
   protected Map<String, String> getParams() throws AuthFailureError {
    Map<String, String> M = new HashMap<>();
    M.put("bookid", id);
    M.put("bname",title);
    M.put("userid", userid);
    M.put("date", date);


    return M;
   }
  };
  RequestQueue requestQueue = Volley.newRequestQueue(this);
  requestQueue.add(request);

 }

 public void mess() {

  // Toast.makeText(BookDetailsActivity.this, "1", Toast.LENGTH_SHORT).show();
  String msg= "order for book rental has been successfully placed.. at the time of return please bring the mobile which is registered with this application." ;

  SmsManager sms= SmsManager.getDefault();

  sms.sendTextMessage(mobile, null, msg, null, null);

 }
}



