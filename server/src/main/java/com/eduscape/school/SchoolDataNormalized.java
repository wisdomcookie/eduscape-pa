package com.eduscape.school;

public class SchoolDataNormalized {

    private final String name;
    private final double graduationRate;
    private final double graduationRatePercentile;
    private final double dropoutRate;
    private final double dropoutRatePercentile;
    private final double spendingPerStudent;
    private final double spendingPerPercentile;
    private final double facultyToStudentRatio;
    private final double facultyToStudentRatioPercentile;
    private final double avgTeacherEducationLevel;
    private final double avgTeacherEducationLevelPercentile;

    SchoolDataNormalized(String name, double graduationRate, double graduationRatePercentile, double dropoutRate, double dropoutRatePercentile, double spendingPerStudent, double spendingPerPercentile, double facultyToStudentRatio, double facultyToStudentRatioPercentile, double avgTeacherEducationLevel, double avgTeacherEducationLevelPercentile) {
        this.name = name;
        this.graduationRate = graduationRate;
        this.graduationRatePercentile = graduationRatePercentile;
        this.dropoutRate = dropoutRate;
        this.dropoutRatePercentile = dropoutRatePercentile;
        this.spendingPerStudent = spendingPerStudent;
        this.spendingPerPercentile = spendingPerPercentile;
        this.facultyToStudentRatio = facultyToStudentRatio;
        this.facultyToStudentRatioPercentile = facultyToStudentRatioPercentile;
        this.avgTeacherEducationLevel = avgTeacherEducationLevel;
        this.avgTeacherEducationLevelPercentile = avgTeacherEducationLevelPercentile;
    }

    public String getName() {
        return name;
    }

    public double getGraduationRate() {
        return graduationRate;
    }

    public double getGraduationRatePercentile() {
        return graduationRatePercentile;
    }

    public double getDropoutRate() {
        return dropoutRate;
    }

    public double getDropoutRatePercentile() {
        return dropoutRatePercentile;
    }

    public double getSpendingPerStudent() {
        return spendingPerStudent;
    }

    public double getSpendingPerPercentile() {
        return spendingPerPercentile;
    }

    public double getFacultyToStudentRatio() {
        return facultyToStudentRatio;
    }

    public double getFacultyToStudentRatioPercentile() {
        return facultyToStudentRatioPercentile;
    }

    public double getAvgTeacherEducationLevel() {
        return avgTeacherEducationLevel;
    }

    public double getAvgTeacherEducationLevelPercentile() {
        return avgTeacherEducationLevelPercentile;
    }
}
