package com.example.grocerieslist;

public class DataModel {

    private String item;
    private int amount;

    public DataModel(String item) {
        this.item = item;
        this.amount = 1;
    }

    public String getNameItem() {
        return item;
    }

    public void setAmount(int num) {
        this.amount = num;
    }
}
