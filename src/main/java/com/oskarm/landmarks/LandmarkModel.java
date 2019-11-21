package com.oskarm.landmarks;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * A simple Model Object that simply acts a struct, containing data about an object.
 * It has an constructor, getters, setters and toString() only.
 *
 */

//An annotation is needed for parsing JSON, if a JSON with unknown data comes, ignore any of these extraneous fields.
@JsonIgnoreProperties(ignoreUnknown = true)
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
        return String.format("Landmark[id=%d, name='%s', city='%s']", id, name, city);
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
