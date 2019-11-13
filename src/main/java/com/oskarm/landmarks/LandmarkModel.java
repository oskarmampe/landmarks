package com.oskarm.landmarks;

public class LandmarkModel {
    private long id;
    private String name;
    private String city;

    public LandmarkModel(long id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString(){
        return String.format("Landmark[id=%d, name='%s', city='%s'", id, name, city);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
