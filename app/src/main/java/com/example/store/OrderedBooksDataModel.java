package com.example.store;

public class OrderedBooksDataModel {
    private String rid,bname,rdate;
  public   OrderedBooksDataModel(String rid,String bname,String rdate) {

      this.rid = rid;
      this.bname = bname;
      this.rdate = rdate;

  }

    public String getRid() {
        return rid;
    }

    public String getBname() {
        return bname;
    }

    public String getRdate() {
        return rdate;


    }



}
