package org.example.model;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Gender getGender(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid gender");
    }
}
