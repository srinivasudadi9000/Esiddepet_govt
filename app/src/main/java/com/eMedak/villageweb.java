package com.eMedak;

/**
 * Created by Srinivas on 10/29/2016.
 */

public class villageweb {
    String villageid;
    String villagename;
    villageweb(String villageid, String villagename){
        this.villageid = villageid;
        this.villagename = villagename;
    }

    public String getVillageid() {
        return villageid;
    }

    public String getVillagename() {
        return villagename;
    }
}
