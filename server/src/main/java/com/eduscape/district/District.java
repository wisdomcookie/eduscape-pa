package com.eduscape.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "district")
@NoArgsConstructor
public class District {

    @Id
    @Column(name = "aun")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "county")
    private String county;

    public District(int id, String name, String county) {
        this.id = id;
        this.name = name;
        this.county = county;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
