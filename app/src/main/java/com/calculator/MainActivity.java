package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 final int version = 1;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  ((TextView) findViewById(R.id.appVersionTextView)).setText("v" + version);
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
  findViewById(R.id.equalsButton).setOnClickListener((View v) -> {
   String expression = expressionTextView.getText().toString();
   if (expression.contains("+")) {
    List<String> numbers = Arrays.asList(expression.split("[+]"));
    String expression2 = "=";
    Integer number0 = 0;
    for (String number : numbers) {
     number0 = (number0 + Integer.parseInt(number));
    }
    expression2 = expression2 + number0;
    ((TextView) findViewById(R.id.equalsTextView))
      .setText(expression2);
   }
  });
  findViewById(R.id.resetButton).setOnClickListener((View v) -> {
   expressionTextView.setText("");
  });
 }
}
