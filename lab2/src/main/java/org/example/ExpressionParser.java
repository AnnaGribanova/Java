package org.example;

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

}
