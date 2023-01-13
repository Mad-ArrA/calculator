package com.gbLessons.android1.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView window = findViewById(R.id.window);
        Button zero = findViewById(R.id.btnZero);
        Button dot = findViewById(R.id.btnDot);
        Button one = findViewById(R.id.btnOne);
        Button two = findViewById(R.id.btnTwo);
        Button three = findViewById(R.id.btnThree);
        Button four = findViewById(R.id.btnFour);
        Button five = findViewById(R.id.btnFive);
        Button six = findViewById(R.id.btnSix);
        Button seven = findViewById(R.id.btnSeven);
        Button eight = findViewById(R.id.btnEight);
        Button nine = findViewById(R.id.btnNine);
        Button factorial = findViewById(R.id.btnFact);
        Button clear = findViewById(R.id.btnClear);
        Button plus = findViewById(R.id.btnPlus);
        Button minus = findViewById(R.id.btnMinus);
        Button multiply = findViewById(R.id.btnMultiply);
        Button division = findViewById(R.id.btnDivision);
        Button square = findViewById(R.id.btnSquare);
        Button remains = findViewById(R.id.btnRemains);
        Button exponent = findViewById(R.id.btnExponent);
        Button rightSide = findViewById(R.id.btnRightSide);
        Button leftSide = findViewById(R.id.btnLeftSide);
        Button equal = findViewById(R.id.btnEqual);
        Button delete = findViewById(R.id.btnDelete);

        Calculations calc = new Calculations();
        equal.setOnClickListener(view -> {
            if (!calc.getMathExpression().isEmpty()) {
                window.setText(calc.solvingTheExpression());
                calc.clear();
            }
        });
        clear.setOnClickListener(view -> {
            calc.clear();
            window.setText(calc.expressionToString());
        });
        delete.setOnClickListener(view -> {
            if (!calc.getMathExpression().isEmpty()) {
                String lastElement = calc.getMathExpression().getLast();
                int lastIndex = calc.getMathExpression().lastIndexOf(lastElement);
                if (calc.isOperator(lastElement) || calc.getParentheses().contains(lastElement) ||
                        lastElement.length() == 1)
                    calc.getMathExpression().remove(lastIndex);
                else {
                    String modifiedLastElement = lastElement.substring(0, lastElement.length() - 1);
                    calc.getMathExpression().set(lastIndex, modifiedLastElement);
                }
            }
            window.setText(calc.expressionToString());
        });
        zero.setOnClickListener(view -> {
            calc.add("0");
            window.setText(calc.expressionToString());
        });
        one.setOnClickListener(view -> {
            calc.add("1");
            window.setText(calc.expressionToString());
        });
        two.setOnClickListener(view -> {
            calc.add("2");
            window.setText(calc.expressionToString());
        });
        three.setOnClickListener(view -> {
            calc.add("3");
            window.setText(calc.expressionToString());
        });
        four.setOnClickListener(view -> {
            calc.add("4");
            window.setText(calc.expressionToString());
        });
        five.setOnClickListener(view -> {
            calc.add("5");
            window.setText(calc.expressionToString());
        });
        six.setOnClickListener(view -> {
            calc.add("6");
            window.setText(calc.expressionToString());
        });
        seven.setOnClickListener(view -> {
            calc.add("7");
            window.setText(calc.expressionToString());
        });
        eight.setOnClickListener(view -> {
            calc.add("8");
            window.setText(calc.expressionToString());
        });
        nine.setOnClickListener(view -> {
            calc.add("9");
            window.setText(calc.expressionToString());
        });
        dot.setOnClickListener(view -> {
            calc.add(".");
            window.setText(calc.expressionToString());
        });
        plus.setOnClickListener(view -> {
            calc.add("+");
            window.setText(calc.expressionToString());
        });
        minus.setOnClickListener(view -> {
            calc.add("-");
            window.setText(calc.expressionToString());
        });
        multiply.setOnClickListener(view -> {
            calc.add("x");
            window.setText(calc.expressionToString());
        });
        division.setOnClickListener(view -> {
            calc.add("÷");
            window.setText(calc.expressionToString());
        });
        square.setOnClickListener(view -> {
            calc.add("√");
            window.setText(calc.expressionToString());
        });
        factorial.setOnClickListener(view -> {
            calc.add("!");
            window.setText(calc.expressionToString());
        });
        rightSide.setOnClickListener(view -> {
            String lastElement = calc.getMathExpression().getLast();
            if ((!calc.isOperator(lastElement) || lastElement.equals("%") || lastElement.equals("!"))
                    && !lastElement.equals("(")) {
                calc.add(")");
                window.setText(calc.expressionToString());
            }
        });
        leftSide.setOnClickListener(view -> {
            calc.add("(");
            window.setText(calc.expressionToString());
        });
        remains.setOnClickListener(view -> {
            calc.add("%");
            window.setText(calc.expressionToString());
        });
        exponent.setOnClickListener(view -> {
            calc.add("^");
            window.setText(calc.expressionToString());
        });
    }
}