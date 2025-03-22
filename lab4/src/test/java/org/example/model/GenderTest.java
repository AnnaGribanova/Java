package org.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class GenderTest {

    /**
     * Проверка получения пола по его названию
     */
    @Test
    void testGetGender() {
        Assertions.assertEquals(Gender.MALE, Gender.getGender("Male"));
        Assertions.assertEquals(Gender.FEMALE, Gender.getGender("Female"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Gender.getGender("a");
        });
    }

    /**
     * Проверка преобразования в строку
     */
    @Test
    void testToString() {
        Assertions.assertEquals("Male", Gender.MALE.getName());
        Assertions.assertEquals("Female", Gender.FEMALE.getName());
    }
}