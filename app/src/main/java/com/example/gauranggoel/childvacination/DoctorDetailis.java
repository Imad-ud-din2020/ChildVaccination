package com.example.gauranggoel.childvacination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurang goel on 02-09-2018.
 */

public class DoctorDetailis {

    static List<DoctorDetailis> doctorDetailises = new ArrayList<>();
    String name;
    String phone;
    String hospital;

    public DoctorDetailis(String name, String phone, String hospital) {
        this.name = name;
        this.phone = phone;
        this.hospital = hospital;
    }

    public DoctorDetailis() {

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
