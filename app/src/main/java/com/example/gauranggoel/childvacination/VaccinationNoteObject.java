package com.example.gauranggoel.childvacination;

/**
 * Created by gaurang goel on 06-09-2018.
 */

public class VaccinationNoteObject {
    String Id;
    String name;
    String fee;
    String hospital;
    String notes;

    public VaccinationNoteObject() {

    }

    public VaccinationNoteObject(String id, String name, String fee, String hospital, String notes) {
        Id = id;
        this.name = name;
        this.fee = fee;
        this.hospital = hospital;
        this.notes = notes;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
