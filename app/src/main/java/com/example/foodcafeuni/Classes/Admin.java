package com.example.foodcafeuni.Classes;

public class Admin {
    private String adminNum , name , pass;

    public Admin() {
    }

    public Admin(String adminNum, String name, String pass) {
        this.adminNum = adminNum;
        this.name = name;
        this.pass = pass;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
