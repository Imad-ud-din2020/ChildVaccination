package com.example.gauranggoel.childvacination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurang goel on 02-09-2018.
 */

public class DoctorDetails {

    String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public DoctorDetails(String id, String name, String phone, String hospital) {

        Id = id;
        this.name = name;
        this.phone = phone;
        this.hospital = hospital;
    }

    String name;
    String phone;
    String hospital;

    public DoctorDetails(String name, String phone, String hospital) {
        this.name = name;
        this.phone = phone;
        this.hospital = hospital;
    }

    public DoctorDetails() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
