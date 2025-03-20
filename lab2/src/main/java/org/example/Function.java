package org.example;

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

    private final String name;

    private Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calc(double x);

    public static Function getFunction(String name) {
        for (Function f : values()) {
            if (f.name.equals(name)) {
                return f;
            }
        }
        return null;
    }
}
