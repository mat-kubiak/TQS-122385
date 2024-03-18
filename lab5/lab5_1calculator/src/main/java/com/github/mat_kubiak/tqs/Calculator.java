package com.github.mat_kubiak.tqs;

import java.util.Stack;

public class Calculator {
    Stack<Double> values = new Stack<>();

    public void push(String operator) throws RuntimeException {
        if (values.size() < 2) {
            throw new RuntimeException("There are too few values (" + values.size() + ") to apply an operator (" + operator + ")");
        }

        double a = values.pop();
        double b = values.pop();

        double toPush = switch (operator) {
            case "+" -> b + a;
            case "-" -> b - a;
            case "*" -> b * a;
            case "/" -> b / a;
            default -> throw new IllegalArgumentException("'" + operator + "' is not a valid operator");
        };
        values.push(toPush);
    }

    public void push(double val) {
        values.push(val);
    }

    public double pullValue() throws RuntimeException {
        if (values.size() != 1) {
            throw new RuntimeException("There number of values is not 1 (" + values.size() + ")!");
        }
        return values.pop();
    }
}
