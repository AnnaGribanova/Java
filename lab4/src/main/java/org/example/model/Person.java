package org.example.model;

public class Person {

    private final int id;
    private String name;
    private Gender gender;
    private Department department;
    private int salary;
    private String birthday;

    public Person(int id, String name, Gender gender, Department department, int salary, String birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("Person: ID - %d, Name - %s, Gender - %s, Salary - %d, Birthday - %s, Department - %s",
                id, name, gender.getName(), salary, birthday, department.getName());
    }
}
