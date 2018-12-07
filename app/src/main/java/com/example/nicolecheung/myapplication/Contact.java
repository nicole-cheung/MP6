package com.example.nicolecheung.myapplication;
import java.util.ArrayList;

public class Contact {
    private String name;
    private String phone;
    private int month, day;
    private String message;
    private int time;
    private static ArrayList<Contact> mainList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static ArrayList<Contact> getList() { return mainList; }

    public void addToList() {
        mainList.add(this);
    }

    public String toString() {
        return name + "\n" + month + "/" + day;
    }

    public Contact() {
    }

    public Contact(String n, String p, int m, int d) {
        name = n;
        phone = p;
        month = m;
        day = d;
        message = "Happy birthday!";
    }

    public Contact (String n, String p, int m, int d, String ms) {
        name = n;
        phone = p;
        month = m;
        day = d;
        message = ms;
    }

    public Contact (String n, String p, int m, int d, int t) {
        name = n;
        phone = p;
        month = m;
        day = d;
        time = t;
    }

    public Contact (String n, String p, int m, int d, String ms, int t) {
        name = n;
        phone = p;
        month = m;
        day = d;
        message = ms;
        time = t;
    }
}
