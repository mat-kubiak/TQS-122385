package com.github.mat_kubiak.tqs;

public class Calculator {
    private Double val1 = null;
    private Double val2 = null;
    private String operator = null;

    public void push(String operator) throws IllegalArgumentException {
        this.operator = operator;
    }

    public void push(double val) throws IllegalArgumentException {
        if (val1 == null) {
            val1 = val;
        } else if (val2 == null) {
            val2 = val;
        } else {
            throw new IllegalArgumentException("only two arguments are allowed to the calculator!");
        }
    }

    public double pullValue() throws RuntimeException {
        if (val1 == null || val2 == null) {
            throw new RuntimeException("two arguments are required!");
        }

        double toReturn = 0.0;
        switch (operator) {
            case "+":
                toReturn = val1 + val2;
                break;
            case "-":
                toReturn = val1 - val2;
                break;
            case "*":
                toReturn = val1 * val2;
                break;
            case "/":
                toReturn = val1 / val2;
                break;
            default:
                throw new IllegalArgumentException("'" + operator + "' is not a valid operator");
        }

        val1 = null;
        val2 = null;
        operator = null;

        return toReturn;
    }
}
