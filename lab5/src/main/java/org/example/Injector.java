package org.example;

import org.example.annotations.AutoInjectable;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для внедрения зависимостей с помощью аннотации AutoInjectable
 */
public class Injector {

    private final Properties properties;

    /**
     * Конструктор, загружающий настройки из файла properties
     * @param propertiesPath - путь к файлу конфигурации
     */
    public Injector(String propertiesPath) {
        properties = new Properties();
        try (InputStream input = Injector.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
    }

    /**
     * Внедряет зависимости в переданный объект
     * @param object - объект, в который нужно внедрить зависимости
     * @param <T> - тип объекта
     * @return - объект с внедренными зависимостями
     */
    public <T> T inject(T object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String className = properties.getProperty(field.getType().getName());

                try {
                    Class<?> classImpl = Class.forName(className);
                    Object instanceImpl = classImpl.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(object, instanceImpl);
                } catch (Exception e) {
                    throw new RuntimeException("Произошла ошибка", e);
                }
            }
        }

        return object;
    }

}
