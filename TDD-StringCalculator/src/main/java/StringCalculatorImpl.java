public class StringCalculatorImpl implements StringCalculator {

    @Override
    public int add(String num)  {
        //TODO implement
        int sum=0;
        String delimiter = "";
        String[] values = null;

        if (num.isEmpty()) return 0;

        boolean isNumeric = num.chars().allMatch( Character::isDigit );
        if (isNumeric) sum = Integer.parseInt(num);

        if (!num.contains("\n") && num.contains(",")) {
            values = num.split(",");
            for (String n : values){
                sum += Integer.parseInt(n);
            }
        }

        if(num.startsWith("//")) {
            // String prefix = num.substring(0, 2);
            delimiter = num.substring(2,3);
            num = num.substring(4);
            System.out.println(num);
            values = num.split(delimiter);
            for (String n : values){
                System.out.println("bbbwejewewf");
                System.out.println(n);
                sum += Integer.parseInt(n);
                System.out.println(sum);
            }
        }

        if (num.contains("\n") ) {
            num = num.replace("\n", ",");
            values = num.split(",");
            for (String n : values){
                sum += Integer.parseInt(n);
            }
        }




        return sum;
    }

}
