import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculatorImpl();
    }

    @Test
    public void testEmptyStringReturnsZero() throws Exception {
        Assertions.assertEquals(0, calculator.add(""));
    }
    @Test
    public void testOneNumberInString() throws Exception {
        Assertions.assertEquals(10, calculator.add("10"));
    }
    @Test
    public void testMultipleNumbersInString() throws Exception {
        Assertions.assertEquals(23, calculator.add("10,11,2"));
    }
    @Test
    public void testNewLinesInNumbers() throws Exception {
        Assertions.assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testDifferentDelimiter() throws Exception {
        Assertions.assertEquals(5, calculator.add("//;\n2;3"));
    }


    @Test
    public void testIfNegative() {

        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                calculator.add("2,34,-1\n3");
            }
        });
    }





}
