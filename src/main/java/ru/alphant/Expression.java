package ru.alphant;

import java.util.Objects;

public class Expression {
    boolean isRoman;
    int operandA;
    String operator;
    int operandB;

    public Expression(boolean isRoman, int operandA, String operator, int operandB) {
        this.isRoman = isRoman;
        this.operandA = operandA;
        this.operator = operator;
        this.operandB = operandB;
    }

    public boolean isRoman() {
        return isRoman;
    }

    public int getOperandA() {
        return operandA;
    }

    public String getOperator() {
        return operator;
    }

    public int getOperandB() {
        return operandB;
    }

    @Override
    public int hashCode(){
        return Objects.hash(isRoman, operandA, operator, operandB);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){return true;}
        if(!(o instanceof Expression)){return false;}
        Expression expr = (Expression) o;
        return isRoman == expr.isRoman &&
                operandA == expr.operandA &&
                operator.equals(expr.operator) &&
                operandB == expr.operandB;
    }


}
