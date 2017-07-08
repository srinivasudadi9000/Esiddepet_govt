package com.eMedak.adapters;

/**
 * Created by Srinivas on 10/29/2016.
 */

public class officers {
    String officerid,officername;
    public officers(String officerid, String officername){
        this.officerid= officerid;this.officername= officername;
    }

    public String getOfficerid() {
        return officerid;
    }

    public String getOfficername() {
        return officername;
    }
}
