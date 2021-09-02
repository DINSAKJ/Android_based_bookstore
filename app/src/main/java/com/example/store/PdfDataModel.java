package com.example.store;

public class PdfDataModel {


        private String pname,plang,pcategory,pcount,pprice,pdf;

        public PdfDataModel(String pname, String plang, String pcategory, String pcount,String pprice,String pdf) {
            this.pname = pname;
            this.plang = plang;
            this.pcategory = pcategory;
            this.pcount = pcount;
            this.pprice= pprice;
            this.pdf = pdf;

        }


        public String getPname() {
            return pname;
        }

        public String getPlang() {
            return plang;
        }

        public String getPcategory() {
            return pcategory;
        }

        public String getPcount() {
            return pcount;
        }

        public String getPprice(){
            return pprice;
        }

        public String getPdf(){
            return pdf;
        }



    }
























