package org.example;

import java.util.Map;
import java.util.Stack;

/**
 * Класс для разбора и вычисления математических выражений
 */
public class ExpressionParser {

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

    private static int getOperatorPriority(Character ch) {
        switch(ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

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
        return 0;
    }

    /**
     * Проверяет правильность записи члена арифметической операции
     * @param member - строковое представление члена арифметической операции
     * @return
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

    private static boolean isMemberContainsChar(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '.';
    }

    private static int skipSpaces(String expression, int i) {
        while(i < expression.length() && expression.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    public static boolean isCorrectExpression(String expression) {
        return !expression.isEmpty() &&
                isCorrectExpression(expression, 0) == expression.length();
    }

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

    public static double parseExpression(String expression, Map<String, Double> variables) {
        if (!isCorrectExpression(expression)) {
            throw new IllegalArgumentException("Expression not correct");
        }
        return parseExpression(expression, variables, 0).value();
    }

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

    private record ResultPair(double value, int index) {
    }
}
