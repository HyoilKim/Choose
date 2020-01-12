package com.example.choose.ui.myPage;

public class WishListItem  {
    private int itemId;
    private String image;
    private String title;
    private String price;

    public WishListItem(String image, String title, String price, int itemId) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.itemId = itemId;
    }

    public String getImage() { return image; }
    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public int getItemId() {return itemId;}

    public void setImage(String image) { this.image = image; }
    public void setTitle(String title) { this.title = title; }
    public void setPrice(String price) { this.price = price; }
    public void setItemId(int itemId) {this.itemId = itemId; }
}
