package ru.alphant;

public record Expression(boolean isRoman, int operandA, String operator, int operandB) {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Expression expr)) {
            return false;
        }
        return isRoman == expr.isRoman &&
                operandA == expr.operandA &&
                operator.equals(expr.operator) &&
                operandB == expr.operandB;
    }


}
