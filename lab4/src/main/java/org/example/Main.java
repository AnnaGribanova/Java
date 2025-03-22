package org.example;

import org.example.model.Person;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        List<Person> persons = csvReader.readFromFile("foreign_names.csv");
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}