package com.eduscape.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.NoArgsConstructor;

@Entity(name = "district_data")
@IdClass(DistrictDataKey.class)
@NoArgsConstructor
public class DistrictData {

    @Id
    @Column(name = "aun")
    private int districtID;

    @Id
    @Column(name = "year")
    private short year;

    @Column(name = "expenditures")
    private long expenditures;

    @Column(name = "revenue")
    private long revenue;

    @Column(name = "professional_personnel")
    private int professionalPersonnel;

    @Column(name = "average_experience")
    private float averageExperience;

    @Column(name = "average_salary")
    private int averageSalary;

    @Column(name = "average_degree")
    private float averageDegree;

    public DistrictData(int districtID, short year, long expenditures, long revenue, int professionalPersonnel, float averageExperience, int averageSalary, float averageDegree) {
        this.districtID = districtID;
        this.year = year;
        this.expenditures = expenditures;
        this.revenue = revenue;
        this.professionalPersonnel = professionalPersonnel;
        this.averageExperience = averageExperience;
        this.averageSalary = averageSalary;
        this.averageDegree = averageDegree;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public long getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(long expenditures) {
        this.expenditures = expenditures;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getProfessionalPersonnel() {
        return professionalPersonnel;
    }

    public void setProfessionalPersonnel(int professionalPersonnel) {
        this.professionalPersonnel = professionalPersonnel;
    }

    public float getAverageExperience() {
        return averageExperience;
    }

    public void setAverageExperience(float averageExperience) {
        this.averageExperience = averageExperience;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }

    public float getAverageDegree() {
        return averageDegree;
    }

    public void setAverageDegree(float averageDegree) {
        this.averageDegree = averageDegree;
    }
}
