package com.example.choose.ui.cart;

public class CartItemData {
    private String title;
    private String price;
    private String image;
    private int count;
    private boolean isCheck;

    public CartItemData(String title, String price, String image, int count, boolean isCheck) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.count = count;
        this.isCheck = isCheck;
    }

    public String getTitle() { return title; }
    public String getImage() { return image; }
    public String getPrice() { return price; }
    public boolean isCheck() { return isCheck; }
    public int getCount() { return count; }

    public void setTitle(String title) { this.title = title; }
    public void setImage(String image) { this.image = image; }
    public void setPrice(String price) { this.price = price; }
    public void setCount(int count) { this.count = count; }
    public void setCheck(boolean check) { isCheck = check; }
}
