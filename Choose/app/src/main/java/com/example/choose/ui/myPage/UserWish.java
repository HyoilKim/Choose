package com.example.choose.ui.myPage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserWish {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user")
    @Expose
    private Integer user;

    @SerializedName("item")
    @Expose
    private Integer item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }
}
