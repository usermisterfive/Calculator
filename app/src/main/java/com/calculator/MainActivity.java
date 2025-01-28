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
  ((TextView) findViewById(R.id.appVersionTextView)).setText("6.0.0");
  TextView expressionTextView = findViewById(R.id.expressionTextView);
  expressionTextView.setText("0");
  initListeners(expressionTextView);
 }
 private void initListeners(TextView expressionTextView) {
  findViewById(R.id.oneButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 1));
  findViewById(R.id.twoButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 2));
  findViewById(R.id.threeButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 3));
  findViewById(R.id.fourButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 4));
  findViewById(R.id.fiveButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 5));
  findViewById(R.id.sixButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 6));
  findViewById(R.id.sevenButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 7));
  findViewById(R.id.eightButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 8));
  findViewById(R.id.nineButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 9));
  findViewById(R.id.zeroButton).setOnClickListener((View v) ->
    Helper.typeDigit(expressionTextView, 0));
  findViewById(R.id.plusButton).setOnClickListener((View v) ->
    expressionTextView.setText(
      Helper.removeDuplicateOperator(expressionTextView) + Operators.PLUS.getSign()));
  findViewById(R.id.minusButton).setOnClickListener((View v) ->
    expressionTextView.setText(
      Helper.removeDuplicateOperator(expressionTextView) + Operators.MINUS.getSign()));
  findViewById(R.id.multiplyButton).setOnClickListener((View v) ->
    expressionTextView.setText(
      Helper.removeDuplicateOperator(expressionTextView) + Operators.MULTIPLY.getSign()));
  findViewById(R.id.divideButton).setOnClickListener((View v) ->
    expressionTextView.setText(
      Helper.removeDuplicateOperator(expressionTextView) + Operators.DIVIDE.getSign()));

  findViewById(R.id.equalsButton).setOnClickListener((View v) ->
    ((TextView) findViewById(R.id.equalsTextView)).setText("="
      + Helper.sumMiltiply(Helper.parse(expressionTextView.getText().toString()))));
  findViewById(R.id.resetButton).setOnClickListener((View v) ->
    expressionTextView.setText(""));
  findViewById(R.id.pointButton).setOnClickListener((View v) -> {
   if (expressionTextView.getText().toString().matches(".*\\d$")) {
    expressionTextView.setText(expressionTextView.getText() + ".");
   }
  });

 }

}
enum Operators {
 PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
 private String sign;
 Operators(String sign) {
  this.sign = sign;
 }
 public String getSign() {
  return sign;
 }

}
