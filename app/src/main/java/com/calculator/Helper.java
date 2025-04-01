package com.calculator;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 static List<List<String>> parse(String expression) {
  List<List<String>> expression3 = new ArrayList<>();
  char[] chars = expression.toCharArray();
  List<String> stringNumbers = new ArrayList<>();
  List<String> symbols = new ArrayList<>();
  int numberId = 0;
  for (int iteration = 0; iteration < chars.length; iteration++) {
   String stringCharacter = String.valueOf(chars[iteration]);
   if (isParsableDouble(stringCharacter) || stringCharacter.equals(".")) {
    if (iteration == 0) {
     symbols.add(Symbols.PLUS.getSymbol());
    }
    if (stringNumbers.size() < numberId + 1) {
     stringNumbers.add("");
    }
    stringNumbers.set(numberId, stringNumbers.get(numberId) + stringCharacter);
   } else {
    if (iteration != 0) {
     numberId++;
    }
    symbols.add(stringCharacter);
   }

  }
  expression3.add(stringNumbers);
  expression3.add(symbols);
  System.out.println("stringNumbers=" + stringNumbers + ", symbols=" + symbols);
  return expression3;
 }
 static double calculate(List<List<String>> list) {
  double sum = 0.0;
  System.out.println("list=" + list);
  if (list.size() == 2) {
   List<String> numbers = list.get(0);
   List<String> symbols = list.get(1);
   List<Double> expression2 = new ArrayList<>();
   multiplyDivide(numbers, symbols, Symbols.MULTIPLY.getSymbol());
   multiplyDivide(numbers, symbols, Symbols.DIVIDE.getSymbol());
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    double number = Double.parseDouble(numbers.get(iteration));
    if (symbols.get(iteration).equals(Symbols.MINUS.toString())) {
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
 static void type(TextView expressionTextView, Integer digit) {
  if (expressionTextView.getText().equals("0")) {
   expressionTextView.setText(String.format(digit.toString()));
  } else {
   expressionTextView.setText(String.format("%s%s", expressionTextView.getText(), digit.toString()));
  }
 }
 static void type(TextView expressionTextView, String symbol) {
  if (expressionTextView.getText().equals("0")) {
   expressionTextView.setText(symbol);
  } else {
   expressionTextView.setText(String.format("%s%s", expressionTextView.getText(), symbol));
  }
 }
 static void multiplyDivide(List<String> numbers, List<String> symbols, String symbol) {
  for (int iteration = 0; iteration < numbers.size(); iteration++) {
   if (symbols.contains(symbol)) {
    int index = symbols.indexOf(symbol);
    double numbersOperated = Double.NaN;
    if (symbol.equals(Symbols.MULTIPLY.getSymbol())) {
     numbersOperated = Double.parseDouble(numbers.get(index))
       * Double.parseDouble(numbers.get(index - 1));
    } else if (symbol.equals(Symbols.DIVIDE.getSymbol())) {
     numbersOperated = Double.parseDouble(numbers.get(index - 1)) /
       Double.parseDouble(numbers.get(index));
    }
    numbers.set(index - 1, String.valueOf(numbersOperated));
    numbers.remove(index);
    symbols.remove(index);
   }
  }
 }
 static String removeDuplicateOperator(TextView expressionTextView) {
  String expression = expressionTextView.getText().toString();
  for (Symbols symbol : Symbols.values()) {
   if (expression.endsWith(symbol.getSymbol())) {
    expression = expression.substring(0, expression.length() - 1);
   }
  }
  return expression;
 }

 /**
  * In this implementation works only with one left bracket and one right bracket.
  * @param expression to process
  * @return modified expression
  */
 static String replaceBracketExpression(String expression) {
  String expression2 = expression;
  if (expression.chars().filter(ch -> ch == '(').count() == 1
    && expression.chars().filter(ch -> ch == ')').count() == 1) {
   expression2 = expression.replace(
     expression.substring(expression.indexOf("("), expression.indexOf(")") + 1),
     String.valueOf(calculate(parse(
       expression.substring(expression.indexOf("(") + 1, expression.indexOf(")"))))));
  }
  return expression2;
 }


}
