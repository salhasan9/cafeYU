package com.example.foodcafeuni.Classes;

public class Orders {
    String date , state, stduentname,studentphone,time,total ,studentid ,resid ;

    public Orders() {
    }

    public Orders(String date, String state, String stduentname, String studentphone, String time, String total, String studentid, String resid) {
        this.date = date;
        this.state = state;
        this.stduentname = stduentname;
        this.studentphone = studentphone;
        this.time = time;
        this.total = total;
        this.studentid = studentid;
        this.resid = resid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStduentname() {
        return stduentname;
    }

    public void setStduentname(String stduentname) {
        this.stduentname = stduentname;
    }

    public String getStudentphone() {
        return studentphone;
    }

    public void setStudentphone(String studentphone) {
        this.studentphone = studentphone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }
}
