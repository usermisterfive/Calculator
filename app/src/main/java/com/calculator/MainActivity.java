package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  ((TextView) findViewById(R.id.appVersionTextView)).setText("v3.1.0");
  TextView expressionTextView = findViewById(R.id.expressionTextView);
  expressionTextView.setText("0");
  findViewById(R.id.oneButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 1));
  findViewById(R.id.twoButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 2));
  findViewById(R.id.threeButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 3));
  findViewById(R.id.fourButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 4));
  findViewById(R.id.fiveButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 5));
  findViewById(R.id.sixButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 6));
  findViewById(R.id.sevenButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 7));
  findViewById(R.id.eightButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 8));
  findViewById(R.id.nineButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 9));
  findViewById(R.id.zeroButton).setOnClickListener((View v) -> typeDigit(expressionTextView, 0));
  findViewById(R.id.plusButton).setOnClickListener((View v) ->
   expressionTextView.setText(expressionTextView.getText() + "+"));
  findViewById(R.id.minusButton).setOnClickListener((View v) ->
   expressionTextView.setText(expressionTextView.getText() + "-"));
  findViewById(R.id.multiplyButton).setOnClickListener((View v) ->
   expressionTextView.setText(expressionTextView.getText() + "*"));
  findViewById(R.id.equalsButton).setOnClickListener((View v) ->
   ((TextView) findViewById(R.id.equalsTextView)).setText("="
     + Helper.sumMiltiply(Helper.parse(expressionTextView.getText().toString()))));
  findViewById(R.id.resetButton).setOnClickListener((View v) ->
   expressionTextView.setText(""));
 }
 void typeDigit(TextView expressionTextView, Integer digit) {
  if (expressionTextView.getText().equals("0")) {
   expressionTextView.setText(digit.toString());
  } else {
   expressionTextView.setText(expressionTextView.getText() + digit.toString());
  }
 }

}
