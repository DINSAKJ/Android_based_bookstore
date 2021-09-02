package com.example.store;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PdfAdapterNew extends RecyclerView.Adapter<PdfAdapterNew.MyViewHolder>implements Filterable {

    private LayoutInflater inflater;
    private ArrayList<PdfDataModel> dataModelArrayList;
    private ArrayList<PdfDataModel> dataModelArrayListFiltered;
    Context c;


    Uri uri;

    public PdfAdapterNew(PdfList pdfList, ArrayList<PdfDataModel> dataModelArrayList) {

        c = pdfList;
        inflater = LayoutInflater.from(pdfList);
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = dataModelArrayList;

    }

    @NonNull
    @Override
    public PdfAdapterNew.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pdf_list_item, parent, false);
        PdfAdapterNew.MyViewHolder holder = new PdfAdapterNew.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PdfAdapterNew.MyViewHolder holder, final int position) {


        //PdfDataModel model =dataModelArrayListFiltered.get(position);
        holder.textView1.setText(dataModelArrayListFiltered.get(position).getPname());
        //holder.textView2.setText(dataModelArrayListFiltered.get(position).getPcategory());
        holder.textView3.setText(dataModelArrayListFiltered.get(position).getPcategory());
       // holder.textView4.setText(dataModelArrayList.get(position).getPcount());
        holder.textView5.setText(dataModelArrayListFiltered.get(position).getPprice());


      String pdfname=dataModelArrayListFiltered.get(position).getPname();
       // final String pdflang=dataModelArrayList.get(position).getPlang();
        String pdfcat=dataModelArrayListFiltered.get(position).getPcategory();
       // final String pdfcount=dataModelArrayList.get(position).getPcount();
         String pdfprice=dataModelArrayListFiltered.get(position).getPprice();
        final String pdf=dataModelArrayListFiltered.get(position).getPdf();

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String pdfname=dataModelArrayListFiltered.get(position).getPname();
              String pdflang=dataModelArrayList.get(position).getPlang();
               String pdfcat=dataModelArrayListFiltered.get(position).getPcategory();
               String pdfcount=dataModelArrayList.get(position).getPcount();
               String pdfprice=dataModelArrayListFiltered.get(position).getPprice();
               String pdf=dataModelArrayListFiltered.get(position).getPdf();


                Intent intent=new Intent(c,NewActivity.class);
                intent.putExtra("art_name",pdfname);
               intent.putExtra("plang",pdflang);
                intent.putExtra("art_category",pdfcat);
                intent.putExtra("pcount",pdfcount);
                intent.putExtra("pprice",pdfprice);
                intent.putExtra("pdf",pdf);

                c.startActivity(intent);


            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  PDFdownload=Config.PdfURL+pdf;

                openchrome(PDFdownload);



            }
        });


        //bind
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
                    ArrayList<PdfDataModel> filteredList = new ArrayList<>();
                    for (PdfDataModel row : dataModelArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPname().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getPcategory().toLowerCase().contains(charString.toLowerCase())) {
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
                dataModelArrayListFiltered = (ArrayList<PdfDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //declare
        TextView textView1,textView2,textView3,textView4,textView5;

        Button button;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //find
            textView1 = itemView.findViewById(R.id.pdfname_list);
            //textView2 = itemView.findViewById(R.id.articlecategory_list);
            textView3= itemView.findViewById(R.id.pdfcategory_list);
           // textView4 = itemView.findViewById(R.id.pdfpagecount_list);
            textView5= itemView.findViewById(R.id.pdfprice_list);

            button=itemView.findViewById(R.id.pdfdwonloadbtn);


        }
    }
    private void openchrome(String urlString){



        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setPackage("com.android.chrome");
        c.startActivity(intent1);

    }







}
