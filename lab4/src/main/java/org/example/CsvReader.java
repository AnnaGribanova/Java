package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.Department;
import org.example.model.Gender;
import org.example.model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {

    public List<Person> readFromFile(String csvFilePath) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(';')
                             .build())
                     .build())  {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            reader.readNext();//пропускаем 1 строку

            List<Person> persons = new ArrayList<>();
            Map<String, Department> departmentMap = new HashMap<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                persons.add(createPerson(nextLine, departmentMap));
            }
            return persons;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Person createPerson(String[] line, Map<String, Department> departmentMap) {
        String departmentName = line[4];
        Department department = departmentMap.get(departmentName);
        if (department == null) {
            department = new Department(departmentName);
            departmentMap.put(departmentName, department);
        }

        Gender gender = Gender.getGender(line[2]);

        return new Person(Integer.parseInt(line[0]), line[1], gender, department,
                Integer.parseInt(line[5]), line[3]);
    }

}
