package com.example.kevin.clemapp.models;

/**
 * Created by Kevin on 19/05/2016.
 */
public class Item {
    private String name;
    private String price;
    private String seller;
    private String url;


    public Item(String name, String price, String seller, String url) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.url = url;
    }

    public String toString() {
        return name+" "+price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
