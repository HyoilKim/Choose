package com.example.choose.ui.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCart {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user")
    @Expose
    private Integer userId;

    @SerializedName("item")
    @Expose
    private Integer itemId;

    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
