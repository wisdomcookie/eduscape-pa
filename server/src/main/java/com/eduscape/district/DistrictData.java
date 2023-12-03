package com.eduscape.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.NoArgsConstructor;

@Entity(name = "district_data")
@IdClass(DistrictDataKey.class)
@NoArgsConstructor
public class DistrictData implements Comparable<DistrictData> {

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

    @Column(name = "enrollment")
    private int enrollment;

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

    public DistrictData(int districtID, short year, long expenditures, long revenue, int professionalPersonnel, float averageExperience, int averageSalary, float averageDegree, int enrollment, float averageDegreePercentile, float averageExperiencePercentile, float averageSalaryPercentile, float spendingPerStudentPercentile, float studentToFacultyRatioPercentile) {
        this.districtID = districtID;
        this.year = year;
        this.expenditures = expenditures;
        this.revenue = revenue;
        this.professionalPersonnel = professionalPersonnel;
        this.averageExperience = averageExperience;
        this.averageSalary = averageSalary;
        this.averageDegree = averageDegree;
        this.enrollment = enrollment;
        this.averageDegreePercentile = averageDegreePercentile;
        this.averageExperiencePercentile = averageExperiencePercentile;
        this.averageSalaryPercentile = averageSalaryPercentile;
        this.spendingPerStudentPercentile = spendingPerStudentPercentile;
        this.studentToFacultyRatioPercentile = studentToFacultyRatioPercentile;
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

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
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

    public String toCSV() {
        return districtID + "," + year + "," + expenditures + "," + revenue + "," + professionalPersonnel + "," +
                averageExperience + "," + averageSalary + "," + averageDegree + "," + enrollment + "\n";
    }

    @Override
    public int compareTo(DistrictData o) {
        if (districtID == o.districtID) {
            return Integer.compare(year, o.year);
        }
        return Integer.compare(districtID, o.districtID);
    }
}
