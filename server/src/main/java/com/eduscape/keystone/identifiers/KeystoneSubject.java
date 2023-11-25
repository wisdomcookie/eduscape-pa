package com.eduscape.keystone.identifiers;

public enum KeystoneSubject {
    ALGEBRA("Algebra I"),
    BIOLOGY("Biology"),
    LITERATURE("Literature");

    public final String identifier;

    KeystoneSubject(String identifier) {
        this.identifier = identifier;
    }

    public static KeystoneSubject get(String identifier) {
        for (KeystoneSubject keystoneSubject : KeystoneSubject.values()) {
            if (identifier.equals(keystoneSubject.identifier)) return keystoneSubject;
        }
        return null;
    }
}
