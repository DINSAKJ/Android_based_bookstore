package com.example.store;

public class MysalesDataModel {



    private String id, title,author,img;

    public MysalesDataModel(String id, String title, String author,String img ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.img = img;
        //this.published = published;
       // this.desc = desc;
       // this.lang = lang;
       // this.aprice =aprice;
       // this.sprice = sprice;
       // this.cno = cno;
        // this.cmail= cmail;


    }

    public String getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

   // public String getPublished() {
       // return published;
    //}

   // public String getDesc() {
      //  return desc;
    //}


    //public String getLang() {
      //  return lang;
   // }

   // public String getAprice() {
       // return aprice;
    //}

    //public String getSprice() {
       // return sprice;
   // }

    //public String getCno() {
      //  return cno;
    //}


    public String getImg() {
        return img;
    }



}























