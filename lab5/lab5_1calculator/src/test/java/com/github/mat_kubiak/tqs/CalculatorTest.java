package com.github.mat_kubiak.tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calc;

    @Given("I have a calculator")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {double} and {double}")
    public void add(double arg1, double arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {double} from {double}")
    public void subtract(double arg1, double arg2) {
        calc.push(arg2);
        calc.push(arg1);
        calc.push("-");
    }

    @When("I multiply {double} and {double}")
    public void multiply(double arg1, double arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {double} by {double}")
    public void divide(double arg1, double arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @Then("I get {double}")
    public void check(double arg1) {
        assertEquals(arg1, calc.pullValue());
    }
}
