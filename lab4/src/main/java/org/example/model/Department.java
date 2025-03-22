package org.example.model;

/**
 * Модель отдела
 */
public class Department {

    /**
     * Переменная для генерации уникального id каждому экземпляру класса
     */
    private static int ID_GENERATOR = 1;

    /**
     * ID отдела - генерируется автоматически в конструкторе
     */
    private final int id;
    /**
     * Название отдела
     */
    private String name;

    /**
     * @param name - название отдела
     */
    public Department(String name) {
        this.id = ID_GENERATOR++;
        this.name = name;
    }

    /**
     * @return - ID отдела
     */
    public int getId() {
        return id;
    }

    /**
     * @return - название отдела
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - новое название отдела
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return - представление отдела в виде строки
     */
    @Override
    public String toString() {
        return String.format("Department [id=%d, name=%s]", id, name);
    }
}
