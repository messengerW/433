package com.example.f433;

import java.io.Serializable;

public class F1_NewsBean implements Serializable {
    public String imgPath;         //   图片地址
    public String title;           //   新闻标题
    public String time;            //   新闻时间
    public String source;          //   新闻来源

    public F1_NewsBean() {
    }

    public F1_NewsBean(String imgPath, String title, String time, String source) {
        this.imgPath = imgPath;
        this.title = title;
        this.time = time;
        this.source = source;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitme() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "F1_NewsBean{" +
                "imgPath='" + imgPath + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
