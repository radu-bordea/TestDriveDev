import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculatorImpl(new DBLogger());
    }

    @Test
    public void testEmptyStringReturnsZero()  {
        Assertions.assertEquals(0, calculator.add(""));
    }
    @Test
    public void testOneNumberInString()  {
        Assertions.assertEquals(10, calculator.add("10"));
    }
    @Test
    public void testMultipleNumbersInString()  {
        Assertions.assertEquals(23, calculator.add("10,11,2"));
    }
    @Test
    public void testNewLinesInNumbers() {
        Assertions.assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testDifferentDelimiter()  {
        Assertions.assertEquals(5, calculator.add("//;\n2;3"));
    }

    @Test
    public void testLoggerIfSumMoreThanOneThousand(){

        Logger mockLogger = mock(Logger.class);
        calculator = new StringCalculatorImpl(mockLogger);
        calculator.add("1001,23");
        verify(mockLogger).log(1024);
    }

    @Test
    public void testCalculatorPrintsToStream(){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        InputStream inputStream = new ByteArrayInputStream(("".getBytes()));
        System.setIn(inputStream);

        PrintStream originalOutputStream = System.out;
        System.setOut(printStream);

        MainApp.main(new String[]{});

        System.setOut(originalOutputStream);

        String output = outputStream.toString();

        StringBuilder stringBuilder = new StringBuilder();

        String welcomeText = "Welcome to Calculator!\n";
        String helpMessage = "Instructions: You must follow the  steps:\n";
        String step1 = "1: Read the task!\n";
        String step2 = "2: Implement the test!\n";
        String step3 = "3: Get the result expected!\n";
        String more = "3\n";

        stringBuilder.append(welcomeText);
        stringBuilder.append(helpMessage).append(step1).append(step2).append(step3);
        stringBuilder.append(more);

        String rez = stringBuilder.toString();

        Assertions.assertEquals(rez, output);

    }
    @Test
    public void printResultFromMessage(){

        String input = "scalc '1,2,3";

        InputStream inputStream = new ByteArrayInputStream((input.getBytes()));
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        MainApp.main(null);

        StringBuilder stringBuilder = new StringBuilder();
        String welcomeText = "Welcome to Calculator!\n";
        String helpMessage = "Instructions: You must follow the  steps:\n";
        String step1 = "1: Read the task!\n";
        String step2 = "2: Implement the test!\n";
        String step3 = "3: Get the result expected!\n";
        String more = "3\n";

        stringBuilder.append(welcomeText);
        stringBuilder.append(helpMessage).append(step1).append(step2).append(step3);
        stringBuilder.append(more);

        int valueExpected = 6;
        String messageExpected = "The result is " + valueExpected;
        String finalMessage = stringBuilder.append(messageExpected).toString().trim();
        Assertions.assertEquals(finalMessage, outputStream.toString());

    }

}
