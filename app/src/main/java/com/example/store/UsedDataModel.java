package com.example.store;

public class UsedDataModel {



    private String id, title,author,published,desc,lang,aprice,sprice,cno,cmail,img;

    public UsedDataModel(String id, String title, String author, String published, String desc,String lang,String aprice,String sprice,String cno,String img ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
        this.desc = desc;
        this.lang = lang;
        this.aprice =aprice;
        this.sprice = sprice;
        this.cno = cno;
       // this.cmail= cmail;
        this.img = img;

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

    public String getPublished() {
        return published;
    }

    public String getDesc() {
        return desc;
    }


    public String getLang() {
        return lang;
    }

    public String getAprice() {
        return aprice;
    }

    public String getSprice() {
        return sprice;
    }

    public String getCno() {
        return cno;
    }


    public String getImg() {
        return img;
    }



}














