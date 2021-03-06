package com.example.gauranggoel.childvacination;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by gaurang goel on 26-08-2018.
 */

public class ChildDetails {

    String id;
    String name;
    String email;
    String phone;
    String dob;

    public ChildDetails() {
    }


    public ChildDetails(String name, String email, String phone, String dob) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public ChildDetails(String id, String name, String email, String phone, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}
