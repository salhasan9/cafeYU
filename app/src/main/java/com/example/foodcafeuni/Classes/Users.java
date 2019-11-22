package com.example.foodcafeuni.Classes;

public class Users {
    private String studentNumber, name , password , email,collage;

    public Users() {
    }

    public Users(String studentNumber, String name, String password, String email, String collage) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.password = password;
        this.email = email;
        this.collage = collage;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }
}
