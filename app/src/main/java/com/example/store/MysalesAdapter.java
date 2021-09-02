package com.example.store;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MysalesAdapter extends RecyclerView.Adapter<MysalesAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    String status,message,url=Config.BaseURL+"deletemysales.php";
    private ArrayList<MysalesDataModel> dataModelArrayList;
    Context c;
    public MysalesAdapter(Context ctx,ArrayList<MysalesDataModel> dataModelArrayList){
        c=ctx;
        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayList=dataModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.mysales_listitem,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MysalesDataModel model=dataModelArrayList.get(position);
        //holder.tid.setText(model.getId());
        holder.title.setText(model.getTitle());
        holder.author.setText(model.getAuthor());

        if(!TextUtils.isEmpty(model.getImg()))
        {
            String imageURL=Config.ImageURL+model.getImg();
            Picasso.get().load(imageURL).into(holder.iv);
            //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
        }

        holder.cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                  delete(model.getId());
               // Toast.makeText(c, "id"+model.getId(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, author;
        ImageView iv;
        CardView card;
        Button cancel;
        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.btitle);
            author = itemView.findViewById(R.id.bauthor);
            iv = itemView.findViewById(R.id.iv);
            card = itemView.findViewById(R.id.card_view);
            cancel=itemView.findViewById(R.id.cancelbtn);
        }
    }

    private void delete(final String id)
    {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(c, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject j = new JSONObject(response);
                    status = j.getString("status");
                    message = j.getString("message");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (status.equals("1")) {
                    c.startActivity(new Intent(c, HomeActivity.class));
                    Toast.makeText(c,"cancelled successfully",Toast.LENGTH_SHORT).show();
                    ((Activity) c).finish();
                } else {
                    Toast.makeText(c,"Failed to cancel", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, error.toString(), Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> M = new HashMap<>();
                M.put("id",id);

                return M;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(request);


    }
}




































