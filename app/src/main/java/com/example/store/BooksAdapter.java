package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BooksAdapter  extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> implements Filterable{

    private LayoutInflater inflater;
    private ArrayList<BooksDataModel> dataModelArrayList;
    Context c;


    private ArrayList<BooksDataModel> dataModelArrayListFiltered;





    public BooksAdapter(Context ctx, ArrayList<BooksDataModel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;



        this.dataModelArrayListFiltered = dataModelArrayList;



    }

    @Override
    public BooksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.books_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final BooksDataModel model = dataModelArrayListFiltered.get(position);
       // holder.tid.setText(model.getId());
        holder.ttitle.setText(model.getTitle());
        holder.tauthor.setText(model.getAuthor());

        if(!TextUtils.isEmpty(model.getImg()))
        {

            String imageURL = Config.ImageURL + model.getImg();
            Picasso.get().load(imageURL).into(holder.iv);
            //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
        }

     holder.card.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(c,BookDetailsActivity.class);
             intent.putExtra("id",model.getId());
             intent.putExtra("title",model.getTitle());
             intent.putExtra("author",model.getAuthor());
             intent.putExtra("published",model.getpublished());
             intent.putExtra("description",model.getDesc());
             intent.putExtra("category",model.getCat());
             intent.putExtra("language",model.getlang());
             intent.putExtra("img",model.getImg());
             intent.putExtra("page no",model.getPno());
             intent.putExtra("price",model.getPrice());
             intent.putExtra("rtamt",model.getRtamt());

             intent.putExtra("qty",model.getqty());


             c.startActivity(intent);
         }
     });



    }

    @Override
    public int getItemCount() {
        return dataModelArrayListFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataModelArrayListFiltered = dataModelArrayList;
                } else {
                    ArrayList<BooksDataModel> filteredList = new ArrayList<>();
                    for (BooksDataModel row : dataModelArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getCat().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    dataModelArrayListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataModelArrayListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataModelArrayListFiltered = (ArrayList<BooksDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
     TextView tid,ttitle,tauthor,tpublished,tdes,tcategory,tlang,timg,tpgno,tprice,trtamt,tqty;
     ImageView iv;
     CardView card;
         public MyViewHolder(View itemView) {
             super(itemView);
           ttitle=itemView.findViewById(R.id.btitle);
           tauthor=itemView.findViewById(R.id.bauthor);

           iv=itemView.findViewById(R.id.iv);
             card=itemView.findViewById(R.id.card_view);
         }
     }



    }








