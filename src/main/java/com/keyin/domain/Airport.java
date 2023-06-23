package com.keyin.domain;


import java.util.ArrayList;
import java.util.List;

public class Airport {

    private long id;
    private String name;
    private String code;

    private List<Passenger> passengers;

    public Airport(){
        passengers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
