package com.example.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    String status,message,url=Config.BaseURL+"deletearticle.php";
    private ArrayList<MysalesDataModel> dataModelArrayList;
    Context c;
    public MyArticleAdapter(Context ctx,ArrayList<MysalesDataModel> dataModelArrayList){
        c=ctx;
        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayList=dataModelArrayList;
    }



    @NonNull
    @Override
    public MyArticleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyArticleAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {






        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}