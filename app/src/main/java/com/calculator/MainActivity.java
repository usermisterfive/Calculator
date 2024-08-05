package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  ((TextView) findViewById(R.id.appVersionTextView)).setText("v" + "2.0.0");
  TextView expressionTextView = findViewById(R.id.expressionTextView);
  expressionTextView.setText("0");
  findViewById(R.id.oneButton).setOnClickListener((View v) -> {
   Integer digit = 1;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.twoButton).setOnClickListener((View v) -> {
   Integer digit = 2;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.threeButton).setOnClickListener((View v) -> {
   Integer digit = 3;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.fourButton).setOnClickListener((View v) -> {
   Integer digit = 4;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.fiveButton).setOnClickListener((View v) -> {
   Integer digit = 5;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.sixButton).setOnClickListener((View v) -> {
   Integer digit = 6;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.sevenButton).setOnClickListener((View v) -> {
   Integer digit = 7;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.eightButton).setOnClickListener((View v) -> {
   Integer digit = 8;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.nineButton).setOnClickListener((View v) -> {
   Integer digit = 9;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.zeroButton).setOnClickListener((View v) -> {
   Integer digit = 0;
   if (expressionTextView.getText().equals("0")) {
    expressionTextView.setText(digit.toString());
   }
   else {
    expressionTextView.setText(expressionTextView.getText() + digit.toString());
   }});
  findViewById(R.id.plusButton).setOnClickListener((View v) -> {
   expressionTextView.setText(expressionTextView.getText() + "+");
  });
  findViewById(R.id.minusButton).setOnClickListener((View v) -> {
   expressionTextView.setText(expressionTextView.getText() + "-");
  });
  findViewById(R.id.equalsButton).setOnClickListener((View v) -> {
   String expression = expressionTextView.getText().toString();
   sum(parse(expression));
   String expression2 = "=" + sum(parse(expression));
   ((TextView) findViewById(R.id.equalsTextView)).setText(expression2);
  });
  findViewById(R.id.resetButton).setOnClickListener((View v) -> {
   expressionTextView.setText("");
  });
 }
 static int sum(List<Integer> list) {
  int sum = 0;
  for (Integer number: list) {
   sum = sum + number;
  }
  return sum;
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
  if (stringNumbers.size() == operators.size()) {
   for (int iteration = 0; iteration < stringNumbers.size(); iteration++) {
    Integer number = Integer.parseInt(stringNumbers.get(iteration));
    if (operators.get(iteration).equals("-")) {
     number = - number;
    }
    expression2.add(number);
   }
  }
  return expression2;
 }
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

}
