package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Injector injector = new Injector("config.properties");

        try {
            SomeBean sb = injector.inject(new SomeBean());
            sb.foo();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}