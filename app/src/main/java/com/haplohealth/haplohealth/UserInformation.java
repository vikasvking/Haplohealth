package com.haplohealth.haplohealth;

/**
 * Created by vikas on 5/14/17.
 */

public class UserInformation {
    String fName,lName,phone;

    public UserInformation(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }
}
