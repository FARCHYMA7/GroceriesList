package com.example.grocerieslist;

import java.util.ArrayList;

public class UsersClass {
    private String userName;
    private String password;
    private String phone;

    private ArrayList<DataModel> dataSet;

    public UsersClass(String userName, String password, String phone) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.dataSet = new ArrayList<>();
    }
}
