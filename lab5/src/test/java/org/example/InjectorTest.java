package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class InjectorTest {

    /**
     * Проверка на неверное имя файла конфигурации
     */
    @Test
    void testFileNotFound() {
        Assertions.assertThrows(RuntimeException.class, () -> {
           Injector injector = new Injector("a");
        });
    }

}