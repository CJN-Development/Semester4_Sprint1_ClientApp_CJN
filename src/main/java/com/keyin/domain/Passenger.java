package com.keyin.domain;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private long id;
    private String firstName;
    private String lastName;

    private int phoNum;

    private List<Aircraft> allowedAircraft;

    public Passenger(){
        allowedAircraft = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoNum() {
        return phoNum;
    }

    public void setPhoNum(int phoNum) {
        this.phoNum = phoNum;
    }

    public void addAllowedAircraft(Aircraft aircraft) {
        allowedAircraft.add(aircraft);
    }

    public void removeAllowedAircraft(Aircraft aircraft) {
        allowedAircraft.remove(aircraft);
    }

    public List<Aircraft> getAllowedAircrafts() {
        return allowedAircraft;
    }
}
