package com.calculator;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Helper {
 static boolean isParsableDouble(String value) {
  boolean isParsable;
  try {
   Double.parseDouble(value);
   isParsable = true;
  } catch (NumberFormatException numberFormatException) {
   isParsable = false;
  }
  return isParsable;
 }
 static List parse(String expression) {
  List<List<?>> expression3 = new ArrayList<>();
  char[] chars = expression.toCharArray();
  List<String> stringNumbers = new ArrayList<>();
  List<String> operators = new ArrayList<>();
  int numberId = 0;
  for (int iteration = 0; iteration < chars.length; iteration++) {
   String stringCharacter = String.valueOf(chars[iteration]);
   if (isParsableDouble(stringCharacter) || stringCharacter.equals(".")) {
    if (iteration == 0) {
     operators.add(Operators.PLUS.getSign());
    }
    if (stringNumbers.size() < numberId + 1) {
     stringNumbers.add("");
    }
    stringNumbers.set(numberId, stringNumbers.get(numberId) + stringCharacter);
   } else {
    if (iteration != 0) {
     numberId++;
    }
    operators.add(stringCharacter);
   }

  }
  expression3.add(stringNumbers);
  expression3.add(operators);
  System.out.println("stringNumbers=" + stringNumbers + ", operators=" + operators);
  return expression3;
 }
 static double sumMiltiply(List<List<String>> list) {
  Double sum = 0.0;
  System.out.println("list=" + list);
  if (list.size() == 2) {
   List<String> numbers = list.get(0);
   List<String> operators = list.get(1);
   List<Double> expression2 = new ArrayList<>();
   replaceMultiplyDivide(numbers, operators, Operators.MULTIPLY.getSign());
   replaceMultiplyDivide(numbers, operators, Operators.DIVIDE.getSign());
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    Double number = Double.parseDouble(numbers.get(iteration));
    if (operators.get(iteration).equals(Operators.MINUS.getSign())) {
     number = - number;
    }
    expression2.add(number);
   }
   for (Double number: expression2) {
    sum = sum + number;
   }
  }
  return sum;
 }
 static void typeDigit(TextView expressionTextView, Integer digit) {
  if (expressionTextView.getText().equals("0")) {
   expressionTextView.setText(digit.toString());
  } else {
   expressionTextView.setText(expressionTextView.getText() + digit.toString());
  }
 }
 static void replaceMultiplyDivide(List<String> numbers, List<String> operators, String operator) {
  for (int iteration = 0; iteration < numbers.size(); iteration++) {
   if (operators.contains(operator)) {
    int index = operators.indexOf(operator);
    Double numbersOperated = Double.NaN;
    if (operator.equals(Operators.MULTIPLY.getSign())) {
     numbersOperated = Double.parseDouble(numbers.get(index))
       * Double.parseDouble(numbers.get(index - 1));
    } else if (operator.equals(Operators.DIVIDE.getSign())) {
     numbersOperated = Double.parseDouble(numbers.get(index - 1)) /
       Double.parseDouble(numbers.get(index));
    }
    numbers.set(index - 1, numbersOperated.toString());
    numbers.remove(index);
    operators.remove(index);
   }
  }
 }
 static String removeDuplicateOperator(TextView expressionTextView) {
  String expression = expressionTextView.getText().toString();
  for (Operators operator : Operators.values()) {
   if (expression.endsWith(operator.getSign())) {
    expression = expression.substring(0, expression.length() - 1);
   }
  }
  return expression;
 }

}
