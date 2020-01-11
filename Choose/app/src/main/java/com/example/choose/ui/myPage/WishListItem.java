package com.example.choose.ui.myPage;

public class WishListItem  {
    private String image;
    private String title;
    private String price;

    public WishListItem(String image, String title, String price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public String getImage() { return image; }
    public String getTitle() { return title; }
    public String getPrice() { return price; }

    public void setImage(String image) { this.image = image; }
    public void setTitle(String title) { this.title = title; }
    public void setPrice(String price) { this.price = price; }
}
