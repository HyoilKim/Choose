package com.example.choose.ui.home;


public class ItemData {
    public String name;
    public String desc;
    public String image;

    public ItemData(String name, String desc, String image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
