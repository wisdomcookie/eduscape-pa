package com.eduscape.query_objects;

public class SchoolDataNormalized {

    private final String name;
    private final double graduationRate;
    private final double graduationRatePercentile;
    private final double dropoutRate;
    private final double dropoutRatePercentile;
    private final double percentLowIncome;
    private final double percentLowIncomePercentile;
    private final double spendingPerStudent;
    private final double spendingPerPercentile;
    private final double facultyToStudentRatio;
    private final double facultyToStudentRatioPercentile;
    private final double avgTeacherDegreeLevel;
    private final double avgTeacherDegreeLevelPercentile;
    private final double avgTeacherExperience;
    private final double avgTeacherExperiencePercentile;

    public SchoolDataNormalized(String name, double graduationRate, double graduationRatePercentile, double dropoutRate, double dropoutRatePercentile, double perentLowIncome, double perentLowIncomePercentile, double spendingPerStudent, double spendingPerPercentile, double facultyToStudentRatio, double facultyToStudentRatioPercentile, double avgTeacherExperienceLevel, double avgTeacherExperiencePercentile, double avgTeacherEducationLevel, double avgTeacherEducationLevelPercentile) {
        this.name = name;
        this.graduationRate = graduationRate;
        this.graduationRatePercentile = graduationRatePercentile;
        this.dropoutRate = dropoutRate;
        this.dropoutRatePercentile = dropoutRatePercentile;
        this.percentLowIncome = perentLowIncome;
        this.percentLowIncomePercentile = perentLowIncomePercentile;
        this.spendingPerStudent = spendingPerStudent;
        this.spendingPerPercentile = spendingPerPercentile;
        this.facultyToStudentRatio = facultyToStudentRatio;
        this.facultyToStudentRatioPercentile = facultyToStudentRatioPercentile;
        this.avgTeacherDegreeLevel = avgTeacherExperienceLevel;
        this.avgTeacherDegreeLevelPercentile = avgTeacherExperiencePercentile;
        this.avgTeacherExperience = avgTeacherEducationLevel;
        this.avgTeacherExperiencePercentile = avgTeacherEducationLevelPercentile;
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

    public double getPercentLowIncome() {
        return percentLowIncome;
    }

    public double getPercentLowIncomePercentile() {
        return percentLowIncomePercentile;
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

    public double getAvgTeacherDegreeLevel() {
        return avgTeacherDegreeLevel;
    }

    public double getAvgTeacherDegreeLevelPercentile() {
        return avgTeacherDegreeLevelPercentile;
    }

    public double getAvgTeacherExperience() {
        return avgTeacherExperience;
    }

    public double getAvgTeacherExperiencePercentile() {
        return avgTeacherExperiencePercentile;
    }
}
