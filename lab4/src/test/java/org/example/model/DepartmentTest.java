package org.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class DepartmentTest {

    /**
     * Проверка генерации уникального ID
     */
    @Test
    void testCreateId() {
        Assertions.assertEquals(1, new Department("a").getId());
        Assertions.assertEquals(2, new Department("b").getId());
    }

    /**
     * Проверка преобразования в строку
     */
    @Test
    void testToString() {
        Assertions.assertEquals("Department [id=1, name=a]", (new Department("a")).toString());
        Assertions.assertEquals("Department [id=2, name=b]", (new Department("b")).toString());
    }
}