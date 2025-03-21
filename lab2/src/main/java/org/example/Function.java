package org.example;

/**
 * Обработчик математических функций
 */
public enum Function {
    COS("cos") {
        @Override
        public double calc(double x) {
            return Math.cos(x);
        }
    },
    SIN("sin") {
        @Override
        public double calc(double x) {
            return Math.sin(x);
        }
    },
    TG("tg") {
        @Override
        public double calc(double x) {
            return Math.sin(x)/Math.cos(x);
        }
    },
    CTG("ctg") {
        @Override
        public double calc(double x) {
            return Math.cos(x)/Math.sin(x);
        }
    };

    /**
     * Запись ф-ии в математическом выражении
     */
    private final String name;

    /**
     * @param name - имя ф-ии
     */
    private Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @param x - аргумент ф-ии
     * @return - значение ф-ии
     */
    public abstract double calc(double x);

    /**
     * @param name - имя ф-ии
     * @return - ф-ию по ее имени
     */
    public static Function getFunction(String name) {
        for (Function f : values()) {
            if (f.name.equals(name)) {
                return f;
            }
        }
        return null;
    }
}
