package org.example.model;

public class Department {

    private static int ID_GENERATOR = 1;

    private final int id;
    private String name;

    public Department(String name) {
        this.id = ID_GENERATOR++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Department: ID - %d, Name - %s", id, name);
    }
}
