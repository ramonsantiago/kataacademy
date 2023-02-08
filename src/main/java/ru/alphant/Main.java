package ru.alphant;

import java.util.Scanner;

import static ru.alphant.Parser.arabicToRoman;
import static ru.alphant.Parser.parse;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Please input expression for calculate. Format for input: <A> <operator> <B>\s
                 Operands A and B must be Integer numbers from 1 to 10 or from I to X in rome numbers
                 Operator may be once in +, -, *, /
                 For exit send "q\"""");
        Scanner inputScanner = new Scanner(System.in);
        while (inputScanner.hasNext()) {
            String inString = inputScanner.nextLine();
            System.out.println(calc(inString));
        }
    }

    public static String calc(String input){
        if (input.equals("q")) {
            System.exit(0);
        }
        Expression exp = parse(input);
        int res = 0;
        switch (exp.operator()) {
            case "+" -> res = exp.operandA() + exp.operandB();
            case "*" -> res = exp.operandA() * exp.operandB();
            case "-" -> res = exp.operandA() - exp.operandB();
            case "/" -> res = exp.operandA() / exp.operandB();
        }
        if (res < 1 && exp.isRoman()) {
            throw new RuntimeException("Can't represent a negative number or zero in roman numerals");
        }
        if(exp.isRoman()){return arabicToRoman(res);}
        return String.valueOf(res);
    }

}