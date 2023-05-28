import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        StringCalculator calculator = new StringCalculatorImpl(new DBLogger());

        String welcomeText = "Welcome to Calculator!\n";
        String helpMessage = "Instructions: You must follow the  steps:\n";
        String step1 = "1: Read the task!\n";
        String step2 = "2: Implement the test!\n";
        String step3 = "3: Get the result expected!\n";
        String more = "3\n";

        String output = welcomeText + helpMessage + step1 + step2 + step3 + more;
        System.out.println(output.trim());



        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String test =  scanner.nextLine();
            int indexRez = test.indexOf("'");
            test = test.substring(indexRez+1);

            int finalRes = calculator.add(test);
            System.out.print("The result is " + finalRes);
        }



        // System.in:
        //   "scalc '1,2,3"'
        // "scalc '1,2,3'" -> "1,2,3"
        // calculator.add("1,2,3")



    }

}
