package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ExpressionParserTest {

    @Test
    void testIsCorrectExpression() {
        Assertions.assertEquals(ExpressionParser.isCorrectExpression(""), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("1"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("1 + "), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("1 2"), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("1.5 + 2"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("(1 + 2)"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("(1+2)"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("(1 + 2))"), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("((1 * 2)"), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("((1 + 2) + (1 + 2))"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("(1/cos(0))"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("cos(sin(tg(ctg(1))))"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("x + 1"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("x - y"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("x + 145 + 13 * (y + cos(z))"), true);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("x * "), false);
        Assertions.assertEquals(ExpressionParser.isCorrectExpression("x - y.y"), false);
    }

}