package com.eduscape.school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity(name = "school")
@NoArgsConstructor
public class School {

    @Id
    @Column(name = "school_number")
    private int id;

    @Column(name = "aun")
    private int districtID;

    @Column(name = "name")
    private String name;

    public School(int id, int districtID, String name) {
        this.id = id;
        this.districtID = districtID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
