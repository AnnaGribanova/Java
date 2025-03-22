package org.example;

import org.example.model.Person;

import java.util.List;

/**
 * Считывает список людей из файла foreign_names.csv и выводит их в консоль
 */
public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        List<Person> persons = csvReader.readFromFile("foreign_names.csv");
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}