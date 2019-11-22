package com.example.foodcafeuni.Classes;

public class CartClass {
    private  String foodid , foodname,foodprice,resname;

    public CartClass() {
    }

    public CartClass(String foodid, String foodname, String foodprice, String resname) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.resname = resname;
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }
}
