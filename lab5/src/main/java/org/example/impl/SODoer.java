package org.example.impl;

import org.example.interfaces.SomeOtherInterface;

/**
 * Реализация SomeOtherInterface
 */
public class SODoer implements SomeOtherInterface {

    @Override
    public void doSomeOther(){
        System.out.println("C");
    }

}

