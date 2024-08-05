package com.calculator;

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
     operators.add("+");
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
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    if (operators.contains("*")) {
     int index = operators.indexOf("*"); //2
     Double numbersMultiplied = Double.parseDouble(numbers.get(index))
       * Double.parseDouble(numbers.get(index - 1));
     System.out.println("numbersMultiplied=" + numbersMultiplied);
     numbers.set(index - 1, numbersMultiplied.toString());
     numbers.remove(index);
     operators.remove(index);
    }
   }
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    Double number = Double.parseDouble(numbers.get(iteration));
    if (operators.get(iteration).equals("-")) {
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

}
