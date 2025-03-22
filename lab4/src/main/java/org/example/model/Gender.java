package org.example.model;

/**
 * Enum - пол человека
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");

    /**
     * Название пола
     */
    private final String name;

    /**
     * @param name - название пола
     */
    Gender(String name) {
        this.name = name;
    }

    /**
     * @return - название пола
     */
    public String getName() {
        return name;
    }

    /**
     * Вызывает IllegalArgumentException, если пол не найден
     * @param name - назвагие пола
     * @return - пол по его названию
     */
    public static Gender getGender(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid gender");
    }
}
