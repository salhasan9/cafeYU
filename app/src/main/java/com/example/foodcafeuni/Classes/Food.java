package com.example.foodcafeuni.Classes;

public class Food {
    private String name, image, desc, price, resid, resname;

    public Food() {
    }

    public Food(String name, String image, String desc, String price, String resid, String resname) {
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.price = price;
        this.resid = resid;
        this.resname = resname;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

}