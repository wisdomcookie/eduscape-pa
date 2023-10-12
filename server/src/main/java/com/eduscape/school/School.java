package com.eduscape.school;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class School {
    @Id
    private int id; // School ID
    private int aun; // District ID
    private String name;
    private double graduationRate;
    private double dropoutRate;
    private double spendingPerStudent;
    private double facultyToStudentRatio;
    private double averageTeacherEducationLevel;
    private int percentile;
    private int overall_rating;

    public School(int id,
                  int aun,
                  String name,
                  double graduationRate,
                  double dropoutRate,
                  double spendingPerStudent,
                  double facultyToStudentRatio,
                  double averageTeacherEducationLevel,
                  int percentile,
                  int overall_rating) {
        this.id = id;
        this.aun = aun;
        this.name = name;
        this.graduationRate = graduationRate;
        this.dropoutRate = dropoutRate;
        this.spendingPerStudent = spendingPerStudent;
        this.facultyToStudentRatio = facultyToStudentRatio;
        this.averageTeacherEducationLevel = averageTeacherEducationLevel;
        this.percentile = percentile;
        this.overall_rating = overall_rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getGraduationRate() {
        return graduationRate;
    }

    public void setGraduationRate(double graduationRate) {
        this.graduationRate = graduationRate;
    }

    public double getDropoutRate() {
        return dropoutRate;
    }

    public void setDropoutRate(double dropoutRate) {
        this.dropoutRate = dropoutRate;
    }

    public double getSpendingPerStudent() {
        return spendingPerStudent;
    }

    public void setSpendingPerStudent(double spendingPerStudent) {
        this.spendingPerStudent = spendingPerStudent;
    }

    public double getFacultyToStudentRatio() {
        return facultyToStudentRatio;
    }

    public void setFacultyToStudentRatio(double facultyToStudentRatio) {
        this.facultyToStudentRatio = facultyToStudentRatio;
    }

    public double getAverageTeacherEducationLevel() {
        return averageTeacherEducationLevel;
    }

    public void setAverageTeacherEducationLevel(double averageTeacherEducationLevel) {
        this.averageTeacherEducationLevel = averageTeacherEducationLevel;
    }

    public int getPercentile() {
        return percentile;
    }

    public void setPercentile(int percentile) {
        this.percentile = percentile;
    }

    public int getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating(int overall_rating) {
        this.overall_rating = overall_rating;
    }
}
