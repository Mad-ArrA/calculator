package com.gbLessons.android1.calculator;

import java.util.LinkedList;

public class Calculations {

    private final LinkedList<String> mathExpression = new LinkedList<>();
    private String finalResult = "Null";
    private int indexOpeningParenthesis = -1;
    private int indexClosingParenthesis = -1;
    private final String operations = "%!^√x÷+-";
    private final String parentheses = "()";

    public String getParentheses() {
        return parentheses;
    }

    public LinkedList<String> getMathExpression() {
        return mathExpression;
    }

    /**
     * Метод добавления записи в лист выражения
     *
     * @param s передаваемый символ
     */
    public void add(String s) {
        int lastIndex;
        String lastSymbol;
        String specialOperators = "%^!x÷";
        String operatorsForOneNum = "!%√";
        if (mathExpression.isEmpty()) {
            if (isOperator(s) && specialOperators.contains(s)) {
                mathExpression.add("0");
                mathExpression.add(s);
            } else mathExpression.add(s);
        } else {
            lastIndex = mathExpression.lastIndexOf(mathExpression.getLast());
            lastSymbol = mathExpression.getLast();

            if (!isOperator(lastSymbol) && !isOperator(s)
                    && !parentheses.contains(s) && !parentheses.contains(lastSymbol)) {
                mathExpression.set(lastIndex, lastSymbol + s);
            } else {
                if (isOperator(lastSymbol) && isOperator(s) && !parentheses.contains(s)) {
                    if ((mathExpression.size() == 1 || mathExpression.get(lastIndex - 1).equals("("))
                            && specialOperators.contains(s)) {
                        mathExpression.set(lastIndex, "0");
                        mathExpression.add(s);
                    } else mathExpression.set(lastIndex, s);
                } else {
                    if (s.equals("(") && (operatorsForOneNum.contains(lastSymbol)
                            || !isOperator(lastSymbol))) {
                        mathExpression.add("x");
                        mathExpression.add(s);
                    } else if (specialOperators.contains(s) && lastSymbol.equals("(")) {
                        mathExpression.add("0");
                        mathExpression.add(s);
                    } else mathExpression.add(s);
                }
            }
        }
    }

    /**
     * Метод отделения выражений в скобках отдельно от основного выражения.
     * После вычислений, в основное выражение, вместо выражения в скобках добавляется число.
     *
     * @return выражение в скобках
     */
    public LinkedList<String> cutExpInParentheses(LinkedList<String> fullExpression) {
        LinkedList<String> expressionInParentheses = new LinkedList<>();
        indexOpeningParenthesis = fullExpression.indexOf("(");
        for (int i = indexOpeningParenthesis; i <= fullExpression.size(); i++) {
            if (fullExpression.get(i).equals("(")) indexOpeningParenthesis = i;
            if (fullExpression.get(i).equals(")")) {
                indexClosingParenthesis = i;
                break;
            }
        }
        for (int i = indexOpeningParenthesis + 1; i < indexClosingParenthesis; i++) {
            expressionInParentheses.add(fullExpression.get(i));
        }
        return expressionInParentheses;
    }

    /**
     * Метод вычисления выражения в без скобок
     *
     * @return результат вычисления
     */
    public String solvingTheExpression() {
        int rightParenthesesCounter = 0;
        int leftParenthesesCounter = 0;
        for (String s : mathExpression) {
            if (s.equals("(")) leftParenthesesCounter++;
            if (s.equals(")")) rightParenthesesCounter++;
        }
        if (rightParenthesesCounter != leftParenthesesCounter) return "Ошибка записи!";
        if (mathExpression.contains("(") && mathExpression.contains(")")) {
            for (int j = 0; j < rightParenthesesCounter; j++) {
                finalResult = solvingCurrentExpression(cutExpInParentheses(mathExpression));
                mathExpression.set(indexOpeningParenthesis, finalResult);
                mathExpression.subList(indexOpeningParenthesis + 1, indexClosingParenthesis + 1).clear();
                indexOpeningParenthesis = -1;
                indexClosingParenthesis = -1;
            }
            finalResult = solvingCurrentExpression(mathExpression);
        } else {
            finalResult = solvingCurrentExpression(mathExpression);
            mathExpression.clear();
            mathExpression.add(finalResult);
        }
        if (!finalResult.contains(".0") && !(finalResult.lastIndexOf("0") == finalResult.length())) {
            return finalResult;
        } else return String.valueOf(Math.round(Double.parseDouble(finalResult)));
    }

    /**
     * Метод вычисления выражения без скобок с приоритезацией операций
     * @param expression переданное выражение без скобок
     * @return результат выражения без скобок
     */
    private String solvingCurrentExpression(LinkedList<String> expression) {
        for (int o = 0; o < operations.length(); o++) {
            String currentOperator = String.valueOf(operations.charAt(o));
            while (expression.contains(currentOperator)) {
                int operatorIndex = expression.indexOf(currentOperator);
                doOperation(expression, currentOperator, operatorIndex);
            }
        }
        return expression.getFirst();
    }

    /**
     * Метод вычисления конкретной операции в выражении
     *
     * @param operator текущий переданный оператор
     */
    private void doOperation(LinkedList<String> expression, String operator, int index) {
        double x;
        double y;
        double calculationResult;
        switch (operator) {
            case "+":
                x = Double.parseDouble(expression.get(index - 1));
                y = Double.parseDouble(expression.get(index + 1));
                calculationResult = x + y;
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index + 1);
                expression.remove(index);
                break;
            case "-":
                x = Double.parseDouble(expression.get(index - 1));
                y = Double.parseDouble(expression.get(index + 1));
                calculationResult = x - y;
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index + 1);
                expression.remove(index);
                break;
            case "÷":
                x = Double.parseDouble(expression.get(index - 1));
                y = Double.parseDouble(expression.get(index + 1));
                calculationResult = x / y;
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index + 1);
                expression.remove(index);
                break;
            case "x":
                x = Double.parseDouble(expression.get(index - 1));
                y = Double.parseDouble(expression.get(index + 1));
                calculationResult = x * y;
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index + 1);
                expression.remove(index);
                break;
            case "^":
                x = Double.parseDouble(expression.get(index - 1));
                y = Double.parseDouble(expression.get(index + 1));
                calculationResult = Math.pow(x, y);
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index + 1);
                expression.remove(index);
                break;
            case "!":
                x = Double.parseDouble(expression.get(index - 1));
                calculationResult = factorial((int) x);
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index);
                break;
            case "%":
                x = Double.parseDouble(expression.get(index - 1));
                y = 100;
                calculationResult = x / y;
                expression.set(index - 1, String.valueOf(calculationResult));
                expression.remove(index);
                break;
            case "√":
                x = Double.parseDouble(expression.get(index + 1));
                calculationResult = Math.sqrt(x);
                expression.set(index + 1, String.valueOf(calculationResult));
                expression.remove(index);
                break;
        }
    }

    private long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    /**
     * Вывод записи выражения
     *
     * @return выражение
     */
    public StringBuilder expressionToString() {
        StringBuilder output = new StringBuilder();
        for (String s : mathExpression) {
            output.append(s);
        }
        return output;
    }

    /**
     * Метод очистки всех переменных
     */
    public void clear() {
        mathExpression.clear();
        finalResult = "Null";
    }

    public boolean isOperator(String s) {
        return operations.contains(s);
    }
}
