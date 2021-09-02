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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsedBookAdapter extends RecyclerView.Adapter<UsedBookAdapter.MyViewHolder>implements Filterable {


private LayoutInflater inflater;
private ArrayList<UsedDataModel> dataModelArrayList;
        Context c;
    private ArrayList<UsedDataModel> dataModelArrayListFiltered;



public UsedBookAdapter(Context ctx,ArrayList<UsedDataModel> dataModelArrayList){
        c=ctx;
        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayList=dataModelArrayList;
    this.dataModelArrayListFiltered = dataModelArrayList;


}

@Override
public UsedBookAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view=inflater.inflate(R.layout.activity_used_list_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        return holder;
        }

@Override
public void onBindViewHolder(UsedBookAdapter.MyViewHolder holder,final int position){

final UsedDataModel model=dataModelArrayListFiltered.get(position);
        //holder.tid.setText(model.getId());
        holder.title.setText(model.getTitle());
        holder.author.setText(model.getAuthor());

        if(!TextUtils.isEmpty(model.getImg()))
        {

        String imageURL=Config.ImageURL+model.getImg();
        Picasso.get().load(imageURL).into(holder.iv);
        //Toast.makeText(c,imageURL, Toast.LENGTH_SHORT).show();
        }

        holder.card.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view){


        Intent intent = new Intent(c, UsedDetailsActivity.class);
        intent.putExtra("id", model.getId());
        intent.putExtra("title", model.getTitle());
        intent.putExtra("author", model.getAuthor());
        intent.putExtra("published", model.getPublished());
        intent.putExtra("description", model.getDesc());
        intent.putExtra("lang", model.getLang());
        intent.putExtra("aprice", model.getAprice());
        intent.putExtra("sprice", model.getSprice());
        intent.putExtra("cno", model.getCno());
        //intent.putExtra("cmail", model.getCmail());
        intent.putExtra("img", model.getImg());
        c.startActivity(intent);
        }
        });


        }

@Override
public int getItemCount(){
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
                    ArrayList<UsedDataModel> filteredList = new ArrayList<>();
                    for (UsedDataModel row : dataModelArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getAuthor().toLowerCase().contains(charString.toLowerCase())) {
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
                dataModelArrayListFiltered = (ArrayList<UsedDataModel >) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }




    class MyViewHolder extends RecyclerView.ViewHolder {
    TextView id, title, author, published, desc, lang, aprice, sprice, cno;
    ImageView iv;
    CardView card;

    public MyViewHolder(View itemView) {
        super(itemView);


        //tid = itemView.findViewById(R.id.bid);
        title = itemView.findViewById(R.id.btitle);
        author = itemView.findViewById(R.id.bauthor);
        //tpublished= itemView.findViewById(R.id.bpublished);
        //tdesc = itemView.findViewById(R.id.bddesc);
        //tcategory = itemView.findViewById(R.id.bdcategory);
        //tlang= itemView.findViewById(R.id.bdlang);
        //tqrcode= itemView.findViewById(R.id.bdqr);
        //tpgno= itemView.findViewById(R.id.bdpgno);
        //tprice = itemView.findViewById(R.id.bdprice);
        //tqty = itemView.findViewById(R.id.bdqty);


        iv = itemView.findViewById(R.id.iv);
        card = itemView.findViewById(R.id.card_view);

    }

}

}