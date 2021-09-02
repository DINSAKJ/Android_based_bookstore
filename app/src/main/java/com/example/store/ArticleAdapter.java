package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.icu.util.ULocale;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
String pdf;
    private LayoutInflater inflater;
    private ArrayList<ArticleDataModel> dataModelArrayList;
    Context c;

    public ArticleAdapter(Article_list article_list, ArrayList<ArticleDataModel> dataModelArrayList) {
        c =article_list;
        inflater = LayoutInflater.from(article_list);
        this.dataModelArrayList = dataModelArrayList;

    }

    @NonNull
    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.article_list_item, parent, false);
        ArticleAdapter.MyViewHolder holder = new ArticleAdapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.MyViewHolder holder, int position) {

        holder.textView1.setText(dataModelArrayList.get(position).getName());
        //// holder.textView2.setText(dataModelArrayList.get(position).getPlang());
        holder.textView3.setText(dataModelArrayList.get(position).getCategory());
        // holder.textView4.setText(dataModelArrayList.get(position).getPcount());
      // holder.textView5.setText(dataModelArrayList.get(position).getArticle());

        final String articlename=dataModelArrayList.get(position).getName();
        // final String pdflang=dataModelArrayList.get(position).getPlang();
        final String articlecategory=dataModelArrayList.get(position).getCategory();
        // final String pdfcount=dataModelArrayList.get(position).getPcount();
        final String article=dataModelArrayList.get(position).getArticle();




    holder.button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      String  articledownload=Config.ArticleURL+article;

       openchrome(articledownload);



    }
   });




    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2, textView3, textView4, textView5;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.articlename_list);

            textView3 = itemView.findViewById(R.id.articlecategory_list);

            button = itemView.findViewById(R.id.selectbtn);


        }


    }private void openchrome(String urlString){



        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setPackage("com.android.chrome");
        c.startActivity(intent1);

    }



}





