package com.example.myapplication;

public class phonenum_item {
    private int icon;
    private String name;
    private String phonenum;

    public phonenum_item(int icon, String name, String phonenum) {
        this.icon = icon;
        this.name = name;
        this.phonenum = phonenum;
    }

    public int getIcon() {return icon;}
    public String getName() {return name;}
    public String getPhonenum() {return phonenum;}
}
