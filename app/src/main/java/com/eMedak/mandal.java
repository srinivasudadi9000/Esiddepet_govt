package com.eMedak;

/**
 * Created by Srinivas on 10/29/2016.
 */

public class mandal {
    int mandalid,villageid;
    String villagename;
    mandal(int mandaid, String villagename, int villageid){
          this.mandalid = mandaid;this.villageid=villageid;this.villagename=villagename;
    }

    public int getVillageid() {
        return villageid;
    }

    public int getMandalid() {
        return mandalid;
    }

    public String getVillagename() {
        return villagename;
    }
}
