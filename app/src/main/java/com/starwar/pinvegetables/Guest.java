package com.starwar.pinvegetables;

import cn.bmob.v3.BmobObject;

public class Guest extends BmobObject {
    private String guestname;
    private Number guestid;

    public Number getGuestid() {
        return guestid;
    }

    public void setGuestid(Number guestid) {
        this.guestid = guestid;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }
}
