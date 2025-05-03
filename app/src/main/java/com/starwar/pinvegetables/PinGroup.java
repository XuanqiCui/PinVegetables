package com.starwar.pinvegetables;

import cn.bmob.v3.BmobObject;

public class PinGroup extends BmobObject {

    private String vegename;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVegename() {
        return vegename;
    }

    public void setVegename(String vegename) {
        this.vegename = vegename;
    }
}
