package com.eduscape.query_objects;

public class SchoolDataNormalized {

    private final String name;
    private final double dropoutRate;
    private final double dropoutRatePercentile;
    private final double percentLowIncome;
    private final double percentLowIncomePercentile;
    private final double collegeBound;
    private final double collegeBoundPercentile;
    private final double spendingPerStudent;
    private final double spendingPerPercentile;
    private final double studentToFacultyRatio;
    private final double studentToFacultyRatioPercentile;
    private final double avgTeacherDegreeLevel;
    private final double avgTeacherDegreeLevelPercentile;
    private final double avgTeacherExperience;
    private final double avgTeacherExperiencePercentile;
    private final double avgTeacherSalary;
    private final double avgTeacherSalaryPercentile;

    public SchoolDataNormalized(String name, double dropoutRate, double dropoutRatePercentile, double percentLowIncome, double percentLowIncomePercentile, double collegeBound, double collegeBoundPercentile, double spendingPerStudent, double spendingPerPercentile, double studentToFacultyRatio, double studentToFacultyRatioPercentile, double avgTeacherExperienceLevel, double avgTeacherExperiencePercentile, double avgTeacherEducationLevel, double avgTeacherEducationLevelPercentile, double avgTeacherSalary, double avgTeacherSalaryPercentile) {
        this.name = name;
        this.dropoutRate = dropoutRate;
        this.dropoutRatePercentile = dropoutRatePercentile;
        this.percentLowIncome = percentLowIncome;
        this.percentLowIncomePercentile = percentLowIncomePercentile;
        this.collegeBound = collegeBound;
        this.collegeBoundPercentile = collegeBoundPercentile;
        this.spendingPerStudent = spendingPerStudent;
        this.spendingPerPercentile = spendingPerPercentile;
        this.studentToFacultyRatio = studentToFacultyRatio;
        this.studentToFacultyRatioPercentile = studentToFacultyRatioPercentile;
        this.avgTeacherDegreeLevel = avgTeacherExperienceLevel;
        this.avgTeacherDegreeLevelPercentile = avgTeacherExperiencePercentile;
        this.avgTeacherExperience = avgTeacherEducationLevel;
        this.avgTeacherExperiencePercentile = avgTeacherEducationLevelPercentile;
        this.avgTeacherSalary = avgTeacherSalary;
        this.avgTeacherSalaryPercentile = avgTeacherSalaryPercentile;
    }

    public String getName() {
        return name;
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

    public double getCollegeBound() {
        return collegeBound;
    }

    public double getCollegeBoundPercentile() {
        return collegeBoundPercentile;
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

    public double getStudentToFacultyRatio() {
        return studentToFacultyRatio;
    }

    public double getStudentToFacultyRatioPercentile() {
        return studentToFacultyRatioPercentile;
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

    public double getAvgTeacherSalary() {
        return avgTeacherSalary;
    }

    public double getAvgTeacherSalaryPercentile() {
        return avgTeacherSalaryPercentile;
    }
}
