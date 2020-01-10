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
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public boolean isCheck() { return isCheck; }
    public void setCheck(boolean check) { isCheck = check; }
}
