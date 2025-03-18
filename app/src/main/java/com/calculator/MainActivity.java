package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  ((TextView) findViewById(R.id.appVersionTextView)).setText(BuildConfig.VERSION_NAME);
  TextView expressionTextView = findViewById(R.id.expressionTextView);
  expressionTextView.setText("0");
  initListeners(expressionTextView);
 }
 private void initListeners(TextView expressionTextView) {
  findViewById(R.id.oneButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 1));
  findViewById(R.id.twoButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 2));
  findViewById(R.id.threeButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 3));
  findViewById(R.id.fourButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 4));
  findViewById(R.id.fiveButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 5));
  findViewById(R.id.sixButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 6));
  findViewById(R.id.sevenButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 7));
  findViewById(R.id.eightButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 8));
  findViewById(R.id.nineButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 9));
  findViewById(R.id.zeroButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, 0));

  findViewById(R.id.plusButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
      Helper.removeDuplicateOperator(expressionTextView), Symbols.PLUS.getSymbol())));
  findViewById(R.id.minusButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
        Helper.removeDuplicateOperator(expressionTextView), Symbols.MINUS.getSymbol())));
  findViewById(R.id.multiplyButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
      Helper.removeDuplicateOperator(expressionTextView), Symbols.MULTIPLY.getSymbol())));
  findViewById(R.id.divideButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
      Helper.removeDuplicateOperator(expressionTextView), Symbols.DIVIDE.getSymbol())));

  findViewById(R.id.leftBracketButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, Symbols.LEFT_BRACKET.getSymbol()));
  findViewById(R.id.rightBracketButton).setOnClickListener((View v) -> {
   if (expressionTextView.getText().toString().contains(Symbols.LEFT_BRACKET.getSymbol())) {
    Helper.type(expressionTextView, Symbols.RIGHT_BRACKET.getSymbol());
   }
  });

  findViewById(R.id.equalsButton).setOnClickListener((View v) -> {
   String expression = expressionTextView.getText().toString();
   List<Boolean> endsWithOperatorTests = new ArrayList<>();
   do {
    endsWithOperatorTests.clear();
    for (Symbols operator : Symbols.values()) {
     boolean endsWithOperator2 = expression.endsWith(operator.getSymbol());
     if (endsWithOperator2) {
      expression = expression.substring(0, expression.length() - 1);
     }
     endsWithOperatorTests.add(endsWithOperator2);
    }

   } while (endsWithOperatorTests.contains(true));
   expressionTextView.setText(expression);
   ((TextView) findViewById(R.id.equalsTextView)).setText(String.format("%s%s", "=",
      + Helper.calculate(Helper.parse(expression))));
  });

  findViewById(R.id.resetButton).setOnClickListener((View v) ->
    expressionTextView.setText(""));

  findViewById(R.id.pointButton).setOnClickListener((View v) -> {
   if (expressionTextView.getText().toString().matches(".*\\d$")) {
    expressionTextView.setText(String.format("%s%s", expressionTextView.getText(), "."));
   }
  });

  findViewById(R.id.backspaceButton).setOnClickListener((View v) -> {
   String expression = expressionTextView.getText().toString();
   if (!expression.isEmpty()) {
    expression = expression.substring(0, expression.length() - 1);

   }
   expressionTextView.setText(expression);
  });

  findViewById(R.id.copyButton).setOnClickListener((View v) -> {
   ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE))
     .setPrimaryClip(ClipData.newPlainText("",
       expressionTextView.getText().toString()));
  });
  findViewById(R.id.pasteButton).setOnClickListener((View v) -> {
   Optional.ofNullable(((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).getPrimaryClip())
     .filter(Objects::nonNull)
     .filter(clipData -> clipData.getItemCount() > 0)
     .ifPresent(clipData -> expressionTextView.setText(clipData.getItemAt(0).getText()));
  });
 }

}
enum Symbols {
 PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), LEFT_BRACKET("("),
  RIGHT_BRACKET("(");
 private final String symbol;
 Symbols(String symbol) {
  this.symbol = symbol;
 }
 public String getSymbol() {
  return symbol;
 }

}
