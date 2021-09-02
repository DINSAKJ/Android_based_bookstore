package com.example.store;

public class MyArticlesDataModel{

    private String articlename, article;

    public MyArticlesDataModel(String artname, String article ) {
        this.articlename= artname;
        this.article = article;


    }

    public String getName(){
        return articlename;
    }

    public String getArticle() {
        return article;
    }


}
