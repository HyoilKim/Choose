package com.example.choose.ui.home;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemData {
//    public String id;
//    public String name;
//    public String desc;
//    public String image;
//

//    public ItemData(String id, String name, /*String desc,*/ String image) {
//        this.id = id;
//        this.name = name;
//        this.desc = desc;
//        this.image = image;
//    }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    public String getDesc() { return desc; }
//    public void setDesc(String desc) { this.desc = desc; }
//    public String getImage() { return image; }
//    public void setImage(String image) { this.image = image; }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("description")
    @Expose
    private String description;

    public ItemData(Integer id, String name, String category, Integer price, String image, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
