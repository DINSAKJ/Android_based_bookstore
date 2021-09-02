package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderedBookAdapter extends RecyclerView.Adapter<OrderedBookAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<OrderedBooksDataModel> dataModelArrayList;
    Context c;

    public OrderedBookAdapter(Context ctx, ArrayList<OrderedBooksDataModel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;

    }


    @Override
    public OrderedBookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.orderedlist_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull OrderedBookAdapter.MyViewHolder holder, int position) {

        final OrderedBooksDataModel model = dataModelArrayList.get(position);
        // holder.tid.setText(model.getId());
        holder.bname.setText(model.getBname());
        holder.rdate.setText(model.getRdate());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c,FineActivity.class);


                intent.putExtra("book_name",model.getBname());
                intent.putExtra("r_id",model.getRid());
                intent.putExtra("rent_date",model.getRdate());
                c.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return dataModelArrayList.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bname,rdate;
        Button button;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bname=itemView.findViewById(R.id.bname_list);
            rdate=itemView.findViewById(R.id.rdate_list);

            button=itemView.findViewById(R.id.select_btn);

        }
    }
}





