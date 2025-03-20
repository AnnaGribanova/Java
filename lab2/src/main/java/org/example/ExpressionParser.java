package org.example;

import java.util.Map;
import java.util.Stack;

/**
 * Класс для проверки правильности написания, разбора и вычисления математических выражений
 * Поддерживает однобуквенные переменные, передаваемые в формате Map<String, Double>,
 * где key - название переменной, value - ее значение
 * Поддерживает тригонометрические функции cos,sin,tg,ctg
 */
public class ExpressionParser {


    /**
     * Проверяет, является ли символ математическим оператором
     * @param ch - проверяемый символ
     * @return - является ли символ математическим оператором
     */
    private static boolean isOperator(Character ch) {
        switch(ch) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    /**
     * Получение приоритета математической операции, 1 - для + и -, 2 - для * и /
     * @param ch - символ оператора
     * @return - приоритет оператора
     */
    private static int getOperatorPriority(Character ch) {
        switch(ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        throw new IllegalArgumentException("Invalid operator: " + ch);
    }

    /**
     * Применяет математический оператор к паре чисел
     * @param ch - символ оператора
     * @param num2 - второе число
     * @param num1 - первое число
     * @return - результат математической операции
     */
    private static double applyOperator(Character ch, double num2, double num1) {
        switch(ch) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        throw new IllegalArgumentException("Invalid operator: " + ch);
    }

    /**
     * Проверяет правильность записи члена арифметической операции
     * @param member - строковое представление члена арифметической операции
     * @return - является ли строка правильно записанным членом арифметической операции
     */
    private static boolean isValidMember(String member) {
        try {
            Double.parseDouble(member);
            return true;
        } catch (NumberFormatException e) {
        }

        if (Function.getFunction(member) != null) {
            return true;
        }

        if (member.length() == 1 && Character.isLetter(member.charAt(0))) {
            return true;
        }

        return false;
    }

    /**
     * @param c - проверямый символ
     * @return - может ли символ содержаться в члене математической операции
     */
    private static boolean isMemberContainsChar(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '.';
    }


    /**
     * Функция для пропуска пробелов до первого отличного от пробела символа в передаваемой строке
     * @param expression - строка для пропуска пробелов
     * @param i - текущее положение в этой строке
     * @return - новое положение в строке
     */
    private static int skipSpaces(String expression, int i) {
        while(i < expression.length() && expression.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    /**
     * Проверяет правильность записи математического выражения
     * @param expression - разбираемое выражение
     * @return - true, если выражение записано правильно, false - иначе
     */
    public static boolean isCorrectExpression(String expression) {
        return !expression.isEmpty() &&
                isCorrectExpression(expression, 0) == expression.length();
    }

    /**
     * Проверяет правильность записи математического выражения
     * @param expression - разбираемое выражение
     * @param i - текущий индекс в разбираемом выражение
     * @return - индекс, на котором закончился разбор выражения
     */
    private static int isCorrectExpression(String expression, int i) {
        boolean nextMember = true;
        while(i < expression.length() && expression.charAt(i) != ')') {
            if (nextMember) {
                if (expression.charAt(i) != '(') {
                    StringBuilder memberBuilder = new StringBuilder();
                    while (i < expression.length() && isMemberContainsChar(expression.charAt(i))) {
                        memberBuilder.append(expression.charAt(i++));
                    }
                    if (!isValidMember(memberBuilder.toString())) {
                        return -1;
                    }
                    if (Function.getFunction(memberBuilder.toString()) != null) {
                        if (expression.charAt(i++) != '(') {
                            return -1;
                        }
                        if ((i = isCorrectExpression(expression, i)) == -1) {
                            return -1;
                        }
                        if (expression.length() <= i || expression.charAt(i++) != ')') {
                            return -1;
                        }
                    }
                } else {
                    ++i;
                    if ((i = isCorrectExpression(expression, i)) == -1) {
                        return -1;
                    }
                    if (expression.length() <= i || expression.charAt(i++) != ')') {
                        return -1;
                    }
                }
                nextMember = false;
            } else {
                i = skipSpaces(expression, i);
                if (expression.length() <= i || !isOperator(expression.charAt(i++))) {
                    return -1;
                }
                i = skipSpaces(expression, i);
                nextMember = true;
            }
        }

        return nextMember ? -1 : i;
    }

    /**
     * Вычисляет переданное математическое выражение
     * @param expression - выражение в виде строки
     * @param variables - переменные, значения которых понадобятся при вычислении выражения
     * @return - вычисленное значеие выражения
     */
    public static double parseExpression(String expression, Map<String, Double> variables) {
        if (!isCorrectExpression(expression)) {
            throw new IllegalArgumentException("Expression not correct");
        }
        return parseExpression(expression, variables, 0).value();
    }

    /**
     * * Проверяет правильность записи математического выражения
     * Поочередно складывает все числа и операторы в 2 стэка,
     * когда встречает число, кладет его в стэк,
     * когда встречает переменную, получается ее значение по названию и кладет значение в стэк,
     * когда встречает функцию, повторно вызывает parseExpression для выражения этой функции, и кладет результат в стэк,
     * когда встречает оператор, то кладет его в стэк, предварительно проверяя,
     * если на вершине стэка лежит оператор такого же или более высокого приоритета,
     * то берет последние 2 числа и этот оператор, и выполняет арифметическую операцию,
     * затем кладет полученное число в стэк
     * @param expression - разбираемое выражение
     * @param variables - переменные, значения которых понадобятся при вычислении выражения
     * @param i - текущий индекс в разбираемом выражение
     * @return - вычисленное значеие выражения
     */
    private static ResultPair parseExpression(String expression, Map<String, Double> variables, int i) {
        boolean nextMember = true;
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        Function f;
        while(i < expression.length() && expression.charAt(i) != ')') {
            if (nextMember) {
                if (expression.charAt(i) != '(') {
                    StringBuilder memberBuilder = new StringBuilder();
                    while (i < expression.length() && isMemberContainsChar(expression.charAt(i))) {
                        memberBuilder.append(expression.charAt(i++));
                    }
                    if ((f = Function.getFunction(memberBuilder.toString())) != null) {
                        i++;//skip '('
                        ResultPair result = parseExpression(expression, variables, i);
                        i = result.index();
                        numbers.push(result.value());
                        i++;//skip ')'
                    } else if (Character.isLetter(memberBuilder.charAt(0))) {
                        Double value = variables.get(memberBuilder.toString());
                        if (value == null) {
                            throw new IllegalArgumentException(String.format("Variable not found - %s",
                                    memberBuilder.toString()));
                        }
                        numbers.push(value);
                    } else {
                        numbers.push(Double.parseDouble(memberBuilder.toString()));
                    }
                } else {
                    ++i;//skip '('
                    ResultPair result = parseExpression(expression, variables, i);
                    i = result.index();
                    numbers.push(result.value());
                    i++;//skip ')'
                }
                nextMember = false;
            } else {
                i = skipSpaces(expression, i);

                Character operator = expression.charAt(i++);
                if (!operators.isEmpty() &&
                        getOperatorPriority(operators.peek()) >= getOperatorPriority(operator)) {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(operator);
                i = skipSpaces(expression, i);
                nextMember = true;
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return new ResultPair(numbers.pop(), i);
    }

    /**
     * Пара значений используемая для возврата в функции parseExpression
     * @param value - значение вычисляемого выражения
     * @param index - индекс, на котором закончился разбор выражения
     */
    private record ResultPair(double value, int index) {
    }
}
