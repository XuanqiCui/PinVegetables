package com.starwar.pinvegetables;

import cn.bmob.v3.BmobObject;

public class Vegetables extends BmobObject {
    private String vegename;
    private String vegeprice;
    private String vegeweight;
    private Boolean vegestatus;


    public String getVegename() {
        return vegename;
    }

    public void setVegename(String vegename) {
        this.vegename = vegename;
    }

    public String getVegeprice() {
        return vegeprice;
    }

    public void setVegeprice(String vegeprice) {
        this.vegeprice = vegeprice;
    }

    public Boolean getVegestatus() {
        return vegestatus;
    }

    public void setVegestatus(Boolean vegestatus) {
        this.vegestatus = vegestatus;
    }

    public String getVegeweight() {
        return vegeweight;
    }

    public void setVegeweight(String vegeweight) {
        this.vegeweight = vegeweight;
    }
}
