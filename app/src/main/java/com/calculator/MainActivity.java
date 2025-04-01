package com.calculator;

import androidx.annotation.NonNull;
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
      Helper.removeDuplicateOperator(expressionTextView), Symbols.PLUS)));
  findViewById(R.id.minusButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
        Helper.removeDuplicateOperator(expressionTextView), Symbols.MINUS)));
  findViewById(R.id.multiplyButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
      Helper.removeDuplicateOperator(expressionTextView), Symbols.MULTIPLY)));
  findViewById(R.id.divideButton).setOnClickListener((View v) ->
    expressionTextView.setText(String.format("%s%s",
      Helper.removeDuplicateOperator(expressionTextView), Symbols.DIVIDE)));

  findViewById(R.id.leftBracketButton).setOnClickListener((View v) ->
    Helper.type(expressionTextView, Symbols.LEFT_BRACKET.toString()));
  findViewById(R.id.rightBracketButton).setOnClickListener((View v) -> {
   if (expressionTextView.getText().toString().contains(Symbols.LEFT_BRACKET.toString())) {
    Helper.type(expressionTextView, Symbols.RIGHT_BRACKET.toString());
   }
  });

  findViewById(R.id.equalsButton).setOnClickListener((View v) -> {
   String expression = expressionTextView.getText().toString();
   List<Boolean> endsWithOperatorTests = new ArrayList<>();

   do {
    endsWithOperatorTests.clear();
    List<Symbols> symbols = new ArrayList<>(List.of(Symbols.values()));
    symbols.remove(Symbols.RIGHT_BRACKET);
    for (Symbols operator : symbols) {
     boolean endsWithOperator2 = expression.endsWith(operator.toString());
     if (endsWithOperator2) {
      expression = expression.substring(0, expression.length() - 1);
     }
     endsWithOperatorTests.add(endsWithOperator2);
    }

   } while (endsWithOperatorTests.contains(true));

   expressionTextView.setText(expression);

   if (expression.chars().filter(ch -> ch == Symbols.LEFT_BRACKET.getCharacter()).count() == 1
     && expression.chars().filter(ch -> ch == Symbols.RIGHT_BRACKET.getCharacter()).count() == 1) {
    expression = Helper.replaceBracketExpression(expression);
   } else if (expression.contains(Symbols.LEFT_BRACKET.toString())
     || expression.contains(Symbols.RIGHT_BRACKET.toString())) {
    while (expression.contains(Symbols.LEFT_BRACKET.toString())
      || expression.contains(Symbols.RIGHT_BRACKET.toString())) {
     expression = expression.replace(Symbols.LEFT_BRACKET.toString(), "")
       .replace(Symbols.RIGHT_BRACKET.toString(), "");
    }
    expressionTextView.setText(expression);
   }

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

  findViewById(R.id.copyButton).setOnClickListener((View v) ->
   ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE))
     .setPrimaryClip(ClipData.newPlainText("", expressionTextView.getText().toString())));
  findViewById(R.id.pasteButton).setOnClickListener((View v) ->
   Optional.ofNullable(((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).getPrimaryClip())
     .filter(Objects::nonNull)
     .filter(clipData -> clipData.getItemCount() > 0)
     .ifPresent(clipData -> expressionTextView.setText(clipData.getItemAt(0).getText()))
  );
 }

}
enum Symbols {
 PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), LEFT_BRACKET("("),
  RIGHT_BRACKET(")");
 private final String symbol;
 private final Character character;
 Symbols(String symbol) {
  this.symbol = symbol;
  this.character = symbol.toCharArray()[0];
 }
 public Character getCharacter() {
  return character;
 }
 @Override @NonNull
 public String toString() {
  return symbol;
 }

}
