package com.eduscape.keystone.identifiers;

public enum KeystoneGroup {
    ALL("All Students"), // All years
    HISTORICALLY_UNDERPERFORMING("Historically Underperforming"), // 2021 and earlier
    ECONOMICALLY_DISADVANTAGED("Economically Disadvantaged"), // 2022 and after
    ELL("ELL"), // 2022 and after
    IEP("IEP"); // 2022 and after

    public final String identifier;

    KeystoneGroup(String identifier) {
        this.identifier = identifier;
    }

    public static KeystoneGroup get(String identifier) {
        for (KeystoneGroup keystoneGroup : KeystoneGroup.values()) {
            if (identifier.equals(keystoneGroup.identifier)) return keystoneGroup;
        }
        return null;
    }
}
