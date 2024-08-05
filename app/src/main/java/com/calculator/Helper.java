package com.calculator;

import java.util.ArrayList;
import java.util.List;

public class Helper {
 static boolean isParsableInteger(String value) {
  boolean isParsable;
  try {
   Integer.parseInt(value);
   isParsable = true;
  } catch (NumberFormatException numberFormatException) {
   isParsable = false;
  }
  return isParsable;
 }
 static List parse(String expression) {
  List<List<?>> expression3 = new ArrayList<>();
  List<Integer> expression2 = new ArrayList<>();
  char[] chars = expression.toCharArray();
  List<String> stringNumbers = new ArrayList<>();
  List<String> operators = new ArrayList<>();
  int numberId = 0;
  for (int iteration = 0; iteration < chars.length; iteration++) {
   String stringCharacter = String.valueOf(chars[iteration]);
   if (isParsableInteger(stringCharacter)) {
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
 static int sumMiltiply(List<List<String>> list) {
  int sum = 0;
  System.out.println("list=" + list);
  if (list.size() == 2) {
   List<String> numbers = list.get(0);
   List<String> operators = list.get(1);
   List<Integer> expression2 = new ArrayList<>();
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    if (operators.contains("*")) {
     int index = operators.indexOf("*"); //2
     Integer numbersMultiplied = Integer.parseInt(numbers.get(index))
       * Integer.parseInt(numbers.get(index - 1));
     System.out.println("numbersMultiplied=" + numbersMultiplied);
     numbers.set(index - 1, numbersMultiplied.toString());
     numbers.remove(index);
     operators.remove(index);
    }
   }
   for (int iteration = 0; iteration < numbers.size(); iteration++) {
    Integer number = Integer.parseInt(numbers.get(iteration));
    if (operators.get(iteration).equals("-")) {
     number = - number;
    }
    expression2.add(number);
   }
   for (Integer number: expression2) {
    sum = sum + number;
   }
  }
  return sum;
 }

}
