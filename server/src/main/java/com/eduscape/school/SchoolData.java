package com.eduscape.school;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity(name = "school_data")
@IdClass(SchoolDataKey.class)
@NoArgsConstructor
public class SchoolData {

    @Id
    @Column(name = "school_number")
    private int schoolID;

    @Id
    @Column(name = "year")
    private short year;

    @Column(name = "initial_enrollment")
    private int initialEnrollment;

    @Column(name = "dropout_count")
    private int dropoutCount;

    @Column(name = "graduate_count")
    private int graduateCount;

    @Column(name = "college_bound")
    private int collegeBound;

    @Column(name = "total_enrollment")
    private int totalEnrollment;

    @Column(name = "low_income_enrollment")
    private int lowIncomeEnrollment;

    @Column(name = "college_bound_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float collegeBoundPercentile;

    @Column(name = "dropout_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float dropoutPercentile;

    @Column(name = "low_income_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float lowIncomePercentile;

    public SchoolData(int schoolID, short year, int initialEnrollment, int dropoutCount, int graduateCount, int collegeBound, int totalEnrollment, int lowIncomeEnrollment, float collegeBoundPercentile, float dropoutPercentile, float lowIncomePercentile) {
        this.schoolID = schoolID;
        this.year = year;
        this.initialEnrollment = initialEnrollment;
        this.dropoutCount = dropoutCount;
        this.graduateCount = graduateCount;
        this.collegeBound = collegeBound;
        this.totalEnrollment = totalEnrollment;
        this.lowIncomeEnrollment = lowIncomeEnrollment;
        this.collegeBoundPercentile = collegeBoundPercentile;
        this.dropoutPercentile = dropoutPercentile;
        this.lowIncomePercentile = lowIncomePercentile;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public int getInitialEnrollment() {
        return initialEnrollment;
    }

    public void setInitialEnrollment(int initialEnrollment) {
        this.initialEnrollment = initialEnrollment;
    }

    public int getDropoutCount() {
        return dropoutCount;
    }

    public void setDropoutCount(int dropoutCount) {
        this.dropoutCount = dropoutCount;
    }

    public int getGraduateCount() {
        return graduateCount;
    }

    public void setGraduateCount(int graduateCount) {
        this.graduateCount = graduateCount;
    }

    public int getCollegeBound() {
        return collegeBound;
    }

    public void setCollegeBound(int collegeBound) {
        this.collegeBound = collegeBound;
    }

    public int getTotalEnrollment() {
        return totalEnrollment;
    }

    public void setTotalEnrollment(int totalEnrollment) {
        this.totalEnrollment = totalEnrollment;
    }

    public int getLowIncomeEnrollment() {
        return lowIncomeEnrollment;
    }

    public void setLowIncomeEnrollment(int lowIncomeEnrollment) {
        this.lowIncomeEnrollment = lowIncomeEnrollment;
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
