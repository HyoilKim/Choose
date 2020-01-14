package com.example.choose.ui.home;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemData implements Comparable<ItemData> {
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

    @SerializedName("view_pager_image_1")
    @Expose
    private String viewPagerImage1;

    @SerializedName("view_pager_image_2")
    @Expose
    private String viewPagerImage2;

    @SerializedName("view_pager_image_3")
    @Expose
    private String viewPagerImage3;

    @SerializedName("age")
    @Expose
<<<<<<< HEAD
    private int age;

    @SerializedName("detail_view1")
    @Expose
    private String detailView1;

    @SerializedName("detail_view2")
    @Expose
    private String detailView2;

    @SerializedName("detail_view3")
    @Expose
    private String detailView3;
=======
    private Integer age;
>>>>>>> Sort_By_Age

    private ArrayList<String> viewPagerList = new ArrayList<>();

    public ItemData(Integer id, String name, String category, Integer price, String image, String description, String viewPagerImage1,
                    String viewPagerImage2, String viewPagerImage3, int age, String detailView1, String detailView2, String detailView3) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.description = description;
        this.viewPagerImage1 = viewPagerImage1;
        this.viewPagerImage2 = viewPagerImage2;
        this.viewPagerImage3 = viewPagerImage3;
        this.age = age;
        this.detailView1 = detailView1;
        this.detailView2 = detailView2;
        this.detailView3 = detailView3;
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

    public String getViewPagerImage1() {
        return viewPagerImage1;
    }
    public void setViewPagerImage1(String viewPagerImage1) { this.viewPagerImage1 = viewPagerImage1; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getViewPagerImage2() {
        return viewPagerImage2;
    }
    public void setViewPagerImage2(String viewPagerImage2) { this.viewPagerImage2 = viewPagerImage2; }

    public String getViewPagerImage3() {
        return viewPagerImage3;
    }
    public void setViewPagerImage3(String viewPagerImage3) { this.viewPagerImage3 = viewPagerImage3; }

    public ArrayList<String> getViewPagerList() {
        return viewPagerList;
    }

    public void setViewPagerList(ArrayList<String> viewPagerList) {
        this.viewPagerList = viewPagerList;
    }
    
    public String getDetailView1() { return detailView1; }
    public String getDetailView2() { return detailView2; }
    public String getDetailView3() { return detailView3; }

    public void setDetailView1(String detailView1) { this.detailView1 = detailView1; }
    public void setDetailView2(String detailView2) { this.detailView2 = detailView2; }
    public void setDetailView3(String detailView3) { this.detailView3 = detailView3; }

    @Override
    public int compareTo(ItemData o) {
        if(this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}
