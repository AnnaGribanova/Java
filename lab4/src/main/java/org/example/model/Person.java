package org.example.model;

/**
 * Модель человека
 */
public class Person {

    /**
     * ID человека
     */
    private final int id;
    /**
     * Имя
     */
    private String name;
    /**
     * Пол
     */
    private Gender gender;
    /**
     * Отдел, в котором человек работает
     */
    private Department department;
    /**
     * Зарплата
     */
    private int salary;
    /**
     * Дата рождения
     */
    private final String birthday;

    /**
     * @param id - ID человека
     * @param name - имя
     * @param gender - пол
     * @param department - отдел
     * @param salary - зарплата
     * @param birthday - дата рождения
     */
    public Person(int id, String name, Gender gender, Department department, int salary, String birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    /**
     * @return - ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return - имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return - пол
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return - отдел
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @return - зарплата
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @return - дата рождения
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param name - новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param gender - новый пол
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @param department - новый отдел
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @param salary - новая зарплата
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return - представление человека в виде строки
     */
    @Override
    public String toString() {
        return String.format("Person [id=%d, name=%s, gender=%s, salary=%d, " +
                        "birthday=%s, department.id=%d, department.name=%s]",
                id, name, gender.getName(), salary, birthday, department.getId(), department.getName());
    }
}
