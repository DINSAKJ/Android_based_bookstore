package com.example.store;

public class BooksDataModel {

    private String id, title,author,published,desc,cat,lang,img,pno,price,rtamt,qty;

    public BooksDataModel(String id, String title, String author, String published, String description,String category,String lanaguage,String img,String pno,String price,String rtamt,String qty ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
        this.desc = description;
        this.cat = category;
        this.lang = lanaguage;
        this.img =img;
        this.pno = pno;
        this.price = price;
        this.rtamt = rtamt;
        this.qty = qty;

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

    public String getpublished() {
        return published;
    }

    public String getDesc() {
        return desc;
    }

    public String getCat(){
        return cat;
    }

    public String getImg(){
        return img;
    }

    public String getPno() {
        return pno;
    }

    public String getPrice() {
        return price;
    }
    public String getRtamt() {
        return rtamt;
    }

    public String getlang() {
        return lang;
    }

    public String getqty() {
        return qty;
    }

}











