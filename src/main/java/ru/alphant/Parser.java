package ru.alphant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Expression parse(String str) {
        Pattern romanPattern = Pattern.compile("^\\s?(X|V?I{0,3}|I[VX])\\s?([/+*\\-])\\s?(X|V?I{0,3}|I[VX])$");
        Pattern arabicPattern = Pattern.compile("^\\s?(10|[0-9])\\s?([/+*\\-])\\s?(10|[0-9])$");

        Matcher m = arabicPattern.matcher(str);
        if (m.find()) {
            return new Expression(false, parseArabicNums(m.group(1)), m.group(2), parseArabicNums(m.group(3)));
        }
        m = romanPattern.matcher(str);
        if (m.find()) {
            return new Expression(true, parseRomanNums(m.group(1)), m.group(2), parseRomanNums(m.group(3)));
        }
        throw new RuntimeException("Not valid expression [" + str + "]");
    }

    public static int parseRomanNums(String str) {
        return switch (str) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }

    public static int parseArabicNums(String str) {
        return Integer.parseInt(str);
    }

    public static String arabicToRoman(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        if (n >= 100) {
            stringBuilder.append('C');
            n -= 100;
        }
        if (n >= 90) {
            stringBuilder.append("XC");
            n -= 90;
        } //1
        if (n >= 50) {
            stringBuilder.append("L");
            n -= 50;
        } //1
        if (n >= 40) {
            stringBuilder.append("XL");
            n -= 40;
        } //1
        while (n >= 10) {
            stringBuilder.append("X");
            n -= 10;
        } //1-3
        if (n == 9) {
            stringBuilder.append("IX");
            n -= 9;
        } //1
        if (n >= 5) {
            stringBuilder.append("V");
            n -= 5;
        } //1
        if (n >= 4) {
            stringBuilder.append("IV");
            n -= 4;
        } //1
        while (n >= 1) {
            stringBuilder.append("I");
            n -= 1;
        } //1-3
        return stringBuilder.toString();
    }
}
