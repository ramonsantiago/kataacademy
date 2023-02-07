package ru.alphant;

import java.util.Scanner;

import static ru.alphant.Calculator.calculate;
import static ru.alphant.Parser.parse;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please input expression for calculate. Format for input: <A> <operator> <B> " +
                "\n Operands A and B must be Integer numbers from 1 to 10 or from I to X in rome numbers" +
                "\n Operator may be once in +, -, *, /" +
                "\n For exit send \"q\" or empty string");
        Scanner inputScanner = new Scanner(System.in);
        while (inputScanner.hasNext()) {
            String inString = inputScanner.nextLine();
            Expression expression = parse(inString);
            System.out.println(calculate(expression));
        }
    }

}