package org.example;

/**
 * Класс контейнер целых чисел на основе списка в виде массива
 */
public class IntContainer {

    /**
     * Индекс хвоста списка, хранящегося в массиве
     */
    private int currentIndex;

    /**
     * Массив, хранящий элементы списка
     */
    private int[] array;

    /**
     * Конструктор с начальным размером массива по умолчанию - 1
     */
    public IntContainer() {
        this(1);
    }


    /**
     * Конструктор
     * @param initialSize - задает начальный размер массива для хранения элементов
     */
    public IntContainer(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size must be greater than 0");
        }
        array = new int[initialSize];
        currentIndex = 0;
    }


    /**
     * Добавляет целое число в список
     * @param value - значение для вставки в список
     */
    public void add(int value) {
        checkCapacity();
        array[currentIndex++] = value;
    }


    /**
     * Генерирует IllegalArgumentException, если переданный индекс выходит за границы текущего размера списка
     * @param index - индекс элемента в списке
     * @return число, хранящееся по переданному индексу
     */
    public int get(int index) {
        if (index < 0 || index >= currentIndex) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return array[index];
    }

    /**
     * Генерирует IllegalArgumentException, если переданный индекс выходит за границы текущего размера списка
     * @param index - индекс элемента в списке
     */
    public void remove(int index) {
        if (index < 0 || index >= currentIndex) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        for (int i = index; i < currentIndex; i++) {
            array[i] = array[i + 1];
        }
        currentIndex--;
    }

    /**
     * @return текущий размер списка
     */
    public int size() {
        return currentIndex;
    }

    /**
     * @return все элементы списка в виде массива
     */
    public int[] toArray() {
        int[] result = new int[currentIndex];
        System.arraycopy(array, 0, result, 0, currentIndex);
        return result;
    }

    /**
     * @return все значения списка, разделенные пробелом, как строку
     */
    @Override
    public String toString() {
        if (currentIndex > 0) {
            StringBuilder result = new StringBuilder();
            result.append('[').append(array[0]);
            for (int i = 1; i < currentIndex; i++) {
                result.append(", ").append(array[i]);
            }
            result.append(']');
            return result.toString();
        } else {
            return "[]";
        }
    }

    /**
     * Проверяет, есть ли свободное место в массиве
     * В случае его отсутствия, вызывает метод для расширения массива
     */
    private void checkCapacity() {
        if (currentIndex >= array.length) {
            expandArray();
        }
    }


    /**
     * Увеличивает массив для хранения элементов в 2 раза
     */
    private void expandArray() {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }
}
