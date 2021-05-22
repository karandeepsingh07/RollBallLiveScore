package com.karandeepsingh.rollball;

public class NewsClass {
    private String imgUrl;
    private String heading;
    private String newsId;
    private String matter;

    public NewsClass(String imgUrl,String heading){
        this.imgUrl=imgUrl;
        this.heading=heading;
    }
    public NewsClass(){
        NewsClass newsClass=new NewsClass("http://google.com","-");
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getHeading() {
        return heading;
    }

    public String getMatter() {
        return matter;
    }

    public String getNewsId() { return newsId; }
}
