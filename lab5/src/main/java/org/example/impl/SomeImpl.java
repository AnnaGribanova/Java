package org.example.impl;

import org.example.interfaces.SomeInterface;

/**
 * Первая реализация SomeInterface
 */
public class SomeImpl implements SomeInterface {

    @Override
    public void doSomething() {
        System.out.println("A");
    }

}
