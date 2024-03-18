@calc_sample
Feature: Arithmetic
  Background: A Calculator
    Given I have a calculator
  Scenario: Addition
    When I add 2 and 3
    Then I get 5
  Scenario: Subtraction
    When I subtract 2 from 3
    Then I get 1
  Scenario: Multiplication
    When I multiply 4 and 5
    Then I get 20
  Scenario: Division
    When I divide 6 by 2
    Then I get 3
  Scenario: Invalid Operator
    When I put invalid operator after 2 and 6
    Then I get an error