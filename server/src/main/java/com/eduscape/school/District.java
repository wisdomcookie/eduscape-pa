package com.eduscape.school;

public class District {

    private final int aud;
    private final String name;
    private final String county;

    /**
     * Creates a new District
     * @param aud The district's ID
     * @param name The district's name
     * @param county The county it belongs to
     */
    public District(int aud, String name, String county) {
        this.aud = aud;
        this.name = name;
        this.county = county;
    }

    public int getAud() {
        return aud;
    }

    public String getName() {
        return name;
    }

    public String getCounty() {
        return county;
    }

    @Override
    public String toString() {
        return aud + " | " + name + " | " + county;
    }
}
