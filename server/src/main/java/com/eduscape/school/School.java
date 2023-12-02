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

    @Column(name = "college_bound_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float collegeBoundPercentile;

    @Column(name = "dropout_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float dropoutPercentile;

    @Column(name = "low_income_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float lowIncomePercentile;

    public School(int id, int districtID, String name) {
        this.id = id;
        this.districtID = districtID;
        this.name = name;
        collegeBoundPercentile = -1;
        dropoutPercentile = -1;
        lowIncomePercentile = -1;
    }

    public School(int id, int districtID, String name, float collegeBoundPercentile, float dropoutPercentile, float lowIncomePercentile) {
        this.id = id;
        this.districtID = districtID;
        this.name = name;
        this.collegeBoundPercentile = collegeBoundPercentile;
        this.dropoutPercentile = dropoutPercentile;
        this.lowIncomePercentile = lowIncomePercentile;
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

    public float getCollegeBoundPercentile() {
        return collegeBoundPercentile;
    }

    public void setCollegeBoundPercentile(float collegeBoundPercentile) {
        this.collegeBoundPercentile = collegeBoundPercentile;
    }

    public float getDropoutPercentile() {
        return dropoutPercentile;
    }

    public void setDropoutPercentile(float dropoutPercentile) {
        this.dropoutPercentile = dropoutPercentile;
    }

    public float getLowIncomePercentile() {
        return lowIncomePercentile;
    }

    public void setLowIncomePercentile(float lowIncomePercentile) {
        this.lowIncomePercentile = lowIncomePercentile;
    }
}
