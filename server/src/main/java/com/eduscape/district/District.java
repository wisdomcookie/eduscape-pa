package com.eduscape.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(name = "average_degree_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float averageDegreePercentile;

    @Column(name = "average_experience_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float averageExperiencePercentile;

    @Column(name = "average_salary_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float averageSalaryPercentile;

    @Column(name = "spending_per_student_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float spendingPerStudentPercentile;

    @Column(name = "student_to_faculty_ratio_percentile", columnDefinition = "FLOAT DEFAULT -1.0")
    private float studentToFacultyRatioPercentile;

    public District(int id, String name, String county, float averageDegreePercentile, float averageExperiencePercentile, float averageSalaryPercentile, float spendingPerStudentPercentile, float studentToFacultyRatioPercentile) {
        this.id = id;
        this.name = name;
        this.county = county;
        this.averageDegreePercentile = averageDegreePercentile;
        this.averageExperiencePercentile = averageExperiencePercentile;
        this.averageSalaryPercentile = averageSalaryPercentile;
        this.spendingPerStudentPercentile = spendingPerStudentPercentile;
        this.studentToFacultyRatioPercentile = studentToFacultyRatioPercentile;
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

    public float getAverageDegreePercentile() {
        return averageDegreePercentile;
    }

    public void setAverageDegreePercentile(float averageDegreePercentile) {
        this.averageDegreePercentile = averageDegreePercentile;
    }

    public float getAverageExperiencePercentile() {
        return averageExperiencePercentile;
    }

    public void setAverageExperiencePercentile(float averageExperiencePercentile) {
        this.averageExperiencePercentile = averageExperiencePercentile;
    }

    public float getAverageSalaryPercentile() {
        return averageSalaryPercentile;
    }

    public void setAverageSalaryPercentile(float averageSalaryPercentile) {
        this.averageSalaryPercentile = averageSalaryPercentile;
    }

    public float getSpendingPerStudentPercentile() {
        return spendingPerStudentPercentile;
    }

    public void setSpendingPerStudentPercentile(float spendingPerStudentPercentile) {
        this.spendingPerStudentPercentile = spendingPerStudentPercentile;
    }

    public float getStudentToFacultyRatioPercentile() {
        return studentToFacultyRatioPercentile;
    }

    public void setStudentToFacultyRatioPercentile(float studentToFacultyRatioPercentile) {
        this.studentToFacultyRatioPercentile = studentToFacultyRatioPercentile;
    }
}
