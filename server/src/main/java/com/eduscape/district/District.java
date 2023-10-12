package com.eduscape.district;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class District {

    @Id
    private int aun;
    private String name;
    private String county;

    public District(int aun, String name, String county) {
        this.aun = aun;
        this.name = name;
        this.county = county;
    }

    public int getAun() {
        return aun;
    }

    public void setAun(int aun) {
        this.aun = aun;
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
