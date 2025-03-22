package org.example;

import org.example.annotations.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

/**
 * Класс, в который внедряем зависимости
 */
public class SomeBean {

    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Вызывает внедренный зависимости
     */
    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }

}
