package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class IntContainerTest {

    /**
     * Проверяет добавление в контейнер
     */
    @Test
    void testAdd() {
        IntContainer container = new IntContainer();
        for (int i = 5; i > 0; i--) {
            container.add(i);
        }
        Assertions.assertEquals(5, container.size());
        Assertions.assertEquals("[5, 4, 3, 2, 1]", container.toString());
    }

    /**
     * Проверяет получение элементов по индексу
     */
    @Test
    void testGet() {
        IntContainer container = new IntContainer();
        for (int i = 5; i > 0; i--) {
            container.add(i);
        }
        for (int i = 5; i > 0; i--) {
            Assertions.assertEquals(i, container.get(5 - i));
        }
    }

    /**
     * Проверяет удаление элементов по индексу
     */
    @Test
    void testRemove() {
        IntContainer container = new IntContainer();
        for (int i = 5; i > 0; i--) {
            container.add(i);
        }
        container.remove(2);
        Assertions.assertEquals(4, container.size());
        Assertions.assertEquals("[5, 4, 2, 1]", container.toString());
    }
}