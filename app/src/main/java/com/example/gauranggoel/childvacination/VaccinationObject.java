package com.example.gauranggoel.childvacination;

/**
 * Created by gaurang goel on 03-09-2018.
 */

public class VaccinationObject {
    String Id;
    String name;
    String scheduleTime;
    String givenTime;
    String status;

    public VaccinationObject(String Id,String name, String scheduleTime, String givenTime, String status) {
        this.Id=Id;
        this.name = name;
        this.scheduleTime = scheduleTime;
        this.givenTime = givenTime;
        this.status = status;
    }

    public VaccinationObject() {

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

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getGivenTime() {
        return givenTime;
    }

    public void setGivenTime(String givenTime) {
        this.givenTime = givenTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
