package com.starwar.pinvegetables;

import cn.bmob.v3.BmobObject;

public class Vegetables extends BmobObject {
    private String vegename;
    private Number vegeprice;
    private Number vegeweight;
    private Boolean vegestatus;


    public String getVegename() {
        return vegename;
    }

    public void setVegename(String vegename) {
        this.vegename = vegename;
    }

    public Number getVegeprice() {
        return vegeprice;
    }

    public void setVegeprice(Number vegeprice) {
        this.vegeprice = vegeprice;
    }

    public Boolean getVegestatus() {
        return vegestatus;
    }

    public void setVegestatus(Boolean vegestatus) {
        this.vegestatus = vegestatus;
    }

    public Number getVegeweight() {
        return vegeweight;
    }

    public void setVegeweight(Number vegeweight) {
        this.vegeweight = vegeweight;
    }
}
