package org.example;

import org.example.annotations.AutoInjectable;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    private final Properties properties;

    public Injector(String propertiesPath) {
        properties = new Properties();
        try (InputStream input = Injector.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
    }

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
