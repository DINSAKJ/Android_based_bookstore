package com.example.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FineActivity extends AppCompatActivity {

    String upivirtualid="Snehamohanlal10@okaxis", name = "xyz";
    final int UPI_PAYMENT = 0;
    String issuedate1,rid,duedate1,fine1,bname;
    TextView tvbname,tvissuedate,tvduedate,tvfine;
    Button paybtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        tvbname=findViewById(R.id.btitle);
        tvissuedate=findViewById(R.id.bissuedate);
        tvduedate=findViewById(R.id.bduedate);
        tvfine=findViewById(R.id.bfine);
       paybtn=findViewById(R.id.finepaybtn);
       paybtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new SessionManager(FineActivity.this).logoutUser();
               startActivity(new Intent(FineActivity.this,LoginActivity.class));
               finish();
           }
       });









        //paybtn.setOnClickListener(new View.OnClickListener() {
          //  @Override
         //   public void onClick(View view) {
//if(!fine1.equals("0")) {
   // payUsingUpi(name, upivirtualid, "", fine1);
//}
//else {
   // Toast.makeText(FineActivity.this, "no fine", Toast.LENGTH_SHORT).show();
//}

            //}
       // });




        Intent i=getIntent();

        bname=i.getStringExtra("book_name");
        issuedate1=i.getStringExtra("rent_date");
        rid=i.getStringExtra("r_id");


        duedate1 = calculateDueDate();

        //Current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = df.format(c);
//        Toast.makeText(this, "Current_date: " + currentDate, Toast.LENGTH_SHORT).show();

//        int days = getcountofdays("31/1/2020", "8/2/2020");
        int days = getcountofdays(duedate1, currentDate);
//        Toast.makeText(this, "Days: " + days, Toast.LENGTH_SHORT).show();
        if (days > 1) {
            fine1 = String.valueOf(days);
        } else {
            fine1 = "0";
        }

        tvbname.setText(bname);
        tvissuedate.setText(issuedate1);
        tvduedate.setText(duedate1);
        tvfine.setText(fine1);
    }


    private String calculateDueDate() {
        /*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        cal.setTime(sdf.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done*/

        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(sdf.parse(issuedate1)); // Now use today date.
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 14); // Adding 5 days
        String output = sdf.format(c.getTime());
        return output;
    }


    public int getcountofdays(String d,String edat) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date cda=null;
        Date eda=null;
        try
        {
            cda=dateFormat.parse(d);
            eda=dateFormat.parse(edat);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar st=new GregorianCalendar();
        st.setTime(cda);
        Calendar en=new GregorianCalendar();
        en.setTime(eda);
        long diff=en.getTimeInMillis()-st.getTimeInMillis();
        float daycount=(float)diff/(24 * 60 * 60 * 1000);
        return (int) (daycount);
    }


    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

}