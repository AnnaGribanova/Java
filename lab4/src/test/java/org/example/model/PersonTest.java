package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    /**
     * Проверка преобразовния в строку
     */
    @Test
    void testToString() {
        Assertions.assertEquals("Person [id=28281, name=Aahan, gender=Male, salary=4800, " +
                "birthday=15.05.1970, department.id=1, department.name=I]",
                new Person(28281, "Aahan", Gender.MALE,
                    new Department("I"), 4800, "15.05.1970").toString());
    }
}