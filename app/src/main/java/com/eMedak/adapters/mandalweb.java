package com.eMedak.adapters;

/**
 * Created by Srinivas on 10/29/2016.
 */

public class mandalweb {
    int mandalid;
    String mandalname;
    public mandalweb(int mandalid, String mandalname){
        this.mandalid = mandalid;
        this.mandalname = mandalname;
    }

    public int getMandalid() {
        return mandalid;
    }

    public String getMandalname() {
        return mandalname;
    }
}
