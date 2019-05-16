package com.app.tiskakarate;

import java.util.ArrayList;

public class Download_model {

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    String catid;

    public ArrayList<Pdfs> getPdfs() {
        return pdfs;
    }

    public void setPdfs(ArrayList<Pdfs> pdfs) {
        this.pdfs = pdfs;
    }

    ArrayList<Pdfs> pdfs;

    class Pdfs {
        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        String Title,link;

    }
}
