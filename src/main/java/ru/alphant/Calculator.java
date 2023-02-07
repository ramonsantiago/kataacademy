package ru.alphant;

import static ru.alphant.Parser.arabicToRoman;

public class Calculator {
    public static String calculate(Expression exp){
        Number res = 0;
        switch (exp.getOperator()){
            case "+": {
                res = exp.getOperandA() + exp.getOperandB();
                break;
//                if (exp.isRoman()){return arabicToRoman(res.intValue());}
//                return res.toString();
            }
            case "-": {
                res = exp.getOperandA() - exp.getOperandB();
                if(res.intValue() < 0 && exp.isRoman()){throw new RuntimeException("Can't represent a negative number in roman numerals");}
                break;
//                if(exp.isRoman()){return arabicToRoman(res.intValue());}
//                return res.toString();
            }
            case "*": {
                res = exp.getOperandA() * exp.getOperandB();
                break;
//                if(exp.isRoman()){return arabicToRoman(res.intValue());}
//                return res.toString();
            }
            case "/": {
                if(exp.getOperandB() == 0){throw new RuntimeException("Division by zero");}
                res = ((double) exp.getOperandA()) / exp.getOperandB();
                var t = (res.doubleValue() - res.intValue());
                if(!exp.isRoman()) {
                    if (t > 0) {
                        return String.valueOf(res.doubleValue());
                    }
                    return String.valueOf(res.intValue());
                }
                String unc = "";
                if(t > 0) {
                    unc = arabicToRoman((int) (t * 12)) + " ounce.";
                }
                StringBuilder str = new StringBuilder();
                if(res.intValue() > 0) { str.append(arabicToRoman(res.intValue()));}
                if(res.intValue() > 0 && t > 0){str.append(" and ");}
                if(t > 0){str.append(unc);}
                return str.toString();
            }
        }
        if(exp.isRoman()){return arabicToRoman(res.intValue());}
        return res.toString();
    }
}
