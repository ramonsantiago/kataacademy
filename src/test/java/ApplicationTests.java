import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import ru.alphant.Expression;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alphant.Calculator.calculate;
import static ru.alphant.Parser.parse;

public class ApplicationTests {

    @Test
    public void testImportJunit(){
        assertEquals(true,true);
    }


    @Test
    public void parseValidTests(){
        Map<String, Expression> testKit = new HashMap<>();
        // Valid to parse
        testKit.put("1+2", new Expression(false,1, "+", 2));
        testKit.put("2 - 3", new Expression(false, 2, "-", 3));
        testKit.put("10 *8", new Expression(false, 10, "*", 8));
        testKit.put("IV +I", new Expression(true, 4, "+", 1));
        testKit.put("VI*X", new Expression(true, 6, "*", 10));
        testKit.put("X/ III", new Expression(true, 10, "/", 3));

        testKit.forEach((s, expression) -> assertEquals(expression, parse(s)));
    }

    @Test
    public void parseExceptionsTest(){
        Map<String, String> testKit = new HashMap<>();
        // Invalid to parse
        testKit.put("Exit", "Not valid expression [Exit]");
        testKit.put("6+X", "Not valid expression [6+X]");
        testKit.put("*12 10", "Not valid expression [*12 10]");
        testKit.put("70, +, 3", "Not valid expression [70, +, 3]");
        testKit.put("3 % 2", "Not valid expression [3 % 2]");
        testKit.put("XII-C", "Not valid expression [XII-C]");
        testKit.put("1234/ 99998", "Not valid expression [1234/ 99998]");
        testKit.put("XI+III", "Not valid expression [XI+III]");
        // incorrect roman args
        testKit.put("IIX*IIV", "Not valid expression [IIX*IIV]");
        testKit.put("VX/X", "Not valid expression [VX/X]");
        testKit.put("X*VV", "Not valid expression [X*VV]");
        // incorrect arabic args
        testKit.put("11+1", "Not valid expression [11+1]");
        testKit.put("9-09", "Not valid expression [9-09]");

        testKit.forEach((s, expected) -> {
            RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> parse(s));
            Assertions.assertEquals(expected, thrown.getMessage());
        });
    }

    @Test
    public void calculateDivisionByZeroExceptionTest(){
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> calculate(
                new Expression(false, 10, "/", 0))
        );
        Assertions.assertEquals("Division by zero", thrown.getMessage());
    }

    @Test
    public void negativeRomanResulException(){
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> calculate(
                new Expression(true, 4, "-", 5))
        );
        Assertions.assertEquals("Can't represent a negative number in roman numerals", thrown.getMessage());
    }

    @Test
    public void validCalculationTests(){
        Assertions.assertEquals("10", calculate(parse("6+4")));
        Assertions.assertEquals("4", calculate(parse("6- 2")));
        Assertions.assertEquals("24", calculate(parse("6 *4")));
        Assertions.assertEquals("3", calculate(parse("6 / 2")));
        Assertions.assertEquals("X", calculate(parse("VI+IV")));
        Assertions.assertEquals("C", calculate(parse("X*X")));
        Assertions.assertEquals("VIII", calculate(parse("X - II")));
        Assertions.assertEquals("V", calculate(parse("X/II")));
        Assertions.assertEquals("-5", calculate(parse("5-10")));
        Assertions.assertEquals("1.5", calculate(parse("3/2")));
        Assertions.assertEquals("I and VI ounce.", calculate(parse("VI/IV")));
        Assertions.assertEquals("VIII ounce.", calculate(parse("IV/VI")));
    }

}
