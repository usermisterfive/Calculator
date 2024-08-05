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
  TextView helloView = findViewById(R.id.resultTextView);
  helloView.setText("0");
  findViewById(R.id.oneButton).setOnClickListener((View v) -> {
   Integer digit = 1;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.twoButton).setOnClickListener((View v) -> {
   Integer digit = 2;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.threeButton).setOnClickListener((View v) -> {
   Integer digit = 3;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.fourButton).setOnClickListener((View v) -> {
   Integer digit = 4;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.fiveButton).setOnClickListener((View v) -> {
   Integer digit = 5;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.sixButton).setOnClickListener((View v) -> {
   Integer digit = 6;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.sevenButton).setOnClickListener((View v) -> {
   Integer digit = 7;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.eightButton).setOnClickListener((View v) -> {
   Integer digit = 8;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.nineButton).setOnClickListener((View v) -> {
   Integer digit = 9;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.zeroButton).setOnClickListener((View v) -> {
   Integer digit = 0;
   if(helloView.getText().equals("0")) { helloView.setText(digit.toString()); }
   else { helloView.setText(helloView.getText() + digit.toString()); }});
  findViewById(R.id.plusButton).setOnClickListener((View v) -> {
   helloView.setText(helloView.getText() + "+");
  });
  findViewById(R.id.equalsButton).setOnClickListener((View v) -> {
   String expression = helloView.getText().toString();
   if(expression.contains("+")) {
    List<String> numbers = Arrays.asList(expression.split("[+]"));
    ((TextView) findViewById(R.id.equalsTextView))
      .setText("=" + (Integer.parseInt(numbers.get(0)) + (Integer.parseInt(numbers.get(1)))));
   }

  });
 }
}
