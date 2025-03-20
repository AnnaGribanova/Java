package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

class ExpressionParserTest {

    @Test
    void testIsCorrectExpression() {
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression(""));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("1"));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("1 + "));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("1 2"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("1.5 + 2"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("(1 + 2)"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("(1+2)"));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("(1 + 2))"));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("((1 * 2)"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("((1 + 2) + (1 + 2))"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("(1/cos(0))"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("cos(sin(tg(ctg(1))))"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("x + 1"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("x - y"));
        Assertions.assertEquals(true, ExpressionParser.isCorrectExpression("x + 145 + 13 * (y + cos(z))"));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("x * "));
        Assertions.assertEquals(false, ExpressionParser.isCorrectExpression("x - y.y"));
    }

    @Test
    void testParseExpression() {
        Assertions.assertEquals(3.4, ExpressionParser.parseExpression("1 + 2.4", null));
        Assertions.assertEquals(9.0, ExpressionParser.parseExpression("1 + 1.5*4 + 2", null));
        Assertions.assertEquals(21.0, ExpressionParser.parseExpression("1 + 2*2 * 5", null));
        Assertions.assertEquals(1.0, ExpressionParser.parseExpression("1 + cos(0)", null));
        Assertions.assertEquals(35.0, ExpressionParser.parseExpression("2 * (1 + 4) + (5 * (2 + 3) + cos(0))", null));
    }

    @Test
    void testParseExpressionWithVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 1.0);
        variables.put("y", 2.0);
        variables.put("z", 3.0);
        variables.put("a", 0.0);

        Assertions.assertEquals(3.0, ExpressionParser.parseExpression("x + y", variables));
        Assertions.assertEquals(2.0, ExpressionParser.parseExpression("y/x", variables));
        Assertions.assertEquals(0.0, ExpressionParser.parseExpression("(x + y) * cos(a)", variables));
        Assertions.assertEquals(9.0, ExpressionParser.parseExpression("(x + y) * (z - a)", variables));
    }
}