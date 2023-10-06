package com.eduscape.school;

public class School {

    private final int schoolID;
    private final int aun;
    private final String name;

    /**
     * Creates a new school
     * @param schoolID The school's ID
     * @param aun The district's ID that this school belongs to
     * @param name The school's name
     */
    public School(int schoolID, int aun, String name) {
        this.schoolID = schoolID;
        this.aun = aun;
        this.name = name;
    }


    public int getSchoolID() {
        return schoolID;
    }

    public int getAun() {
        return aun;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return schoolID + " | " + aun + " | " + name;
    }
}
