package org.example.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для полей класса
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {}