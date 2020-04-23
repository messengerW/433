package com.example.f433.Activities.Favorites;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class FavBean implements Serializable {
    public String imageId;      //  图片id
    public Drawable image;      //  图片
    public String userId;       //  用户id
    public String userName;     //  用户名
    public Drawable userHead;     //  用户头像
    //  ... 其他属性

    public FavBean() {
    }

    public FavBean(String imageId, Drawable img, String userId, String userName, Drawable head) {
        this.imageId = imageId;
        this.image = img;
        this.userId = userId;
        this.userName = userName;
        this.userHead = head;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserHead(Drawable head) {
        this.userHead = head;
    }

    public Drawable getUserHead() {
        return userHead;
    }
}
