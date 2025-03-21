package org.example;

import java.util.List;

/**
 * Класс для тестирования производительности списков
 */
public class ListTest {

    /**
     * Вычисляет время выполнения операций add,get,remove с заданным количеством итераций
     * @param list - список, над которым будет проведен тест производительности
     * @param iterations - количество итераций для каждой проверямой операции
     * @return - время(в наносекундах) в формате массива long из 3 элементов, где элементы с индексом:
     * 0 - время выполнения операции add
     * 1 - время выполнения операции get
     * 2 - время выполнения операции remove
     */
    public static long[] runTest(List<Integer> list, int iterations) {
        long[] result = new long[3];

        long time = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }
        result[0] = System.nanoTime() - time;

        time = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.get(i);
        }
        result[1] = System.nanoTime() - time;

        time = System.nanoTime();
        for (int i = iterations - 1; i >= 0; i--) {
            list.remove(i);
        }
        result[2] = System.nanoTime() - time;

        return result;
    }
}
