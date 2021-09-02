package com.example.store;

public class ArticleDataModel {

  private String name,category,article;
public ArticleDataModel(String name,String category,String article)

    {
this.name=name;
this.category=category;
this.article=article;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArticle() {
        return article;
    }
}
