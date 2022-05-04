import java.util.*;

public class arunAss02 {
    public static void main(String[] args) {
//        checkValid();

        Scanner inp=new Scanner(System.in);
        String str=inp.next();

        char[] arr=str.toCharArray();
        ArrayList<Character> up=new ArrayList<>();
        ArrayList<Character> low=new ArrayList<>();
        ArrayList<Character> even=new ArrayList<>();
        ArrayList<Character> odd=new ArrayList<>();
        for (char c : arr) {
            if (Character.isDigit(c)){
                if (Character.getNumericValue(c)%2==0)
                    even.add(c);
                else
                    odd.add(c);
            }
            else if (Character.isUpperCase(c))
                up.add(c);
            else if (Character.isLowerCase(c))
                low.add(c);
        }
        Collections.sort(up);
        Collections.sort(low);
        Collections.sort(even);
        Collections.sort(odd);

    }
    public static void checkValid(){
        Scanner inp=new Scanner(System.in);
        String main=inp.next();
        int len=main.length();
        if (len!=12){
            System.out.println("Invalid input");
            return;
        }
        if (Character.isUpperCase(main.charAt(0)) && Character.isUpperCase(main.charAt(1)) && Character.isUpperCase(main.charAt(3)) && Character.isDigit(main.charAt(2))){
            int year=Integer.parseInt(main.substring(4,8));
            int month=Integer.parseInt(main.substring(8,10));
            int day=Integer.parseInt(main.substring(10));
//            System.out.println(year+" "+ month+" "+day);

            if (year<2015 || year>2020){
                System.out.println("Invalid year");
                return;
            }
            else if (month<1 || month>12){
                System.out.println("Invalid month");
                return;
            }
            else if (day<1 || day>31){
                System.out.println("Invalid date");
                return;
            }
            else {
                System.out.println("Batch Number: "+main.substring(0,4));
                System.out.println("Expiry Date: "+day+"/"+month+"/"+year);
            }

        }
        else{
            System.out.println("Invalid Batch Code");
        }
    }

}

/*
rt and Sons has opened a new Kernel Sack selling shop. They want to arrange the sacks in an order as which perishes first.
  It was a twelve-digit package code where the first four digits are Batch Number. Batch number is valid only if the first,
   second and fourth characters should be letters of the alphabet (Uppercase) and the third character should be a number.

    The next 8 digits represent the Kernel sack expiry date in YYYYMMDD format. Generate a Java code to extract the expiry date
     from package code and display the same along with the Batch Number.


Note:

The date should be displayed in DD/MM/YYYY format.
The input string length should be exactly 12. Otherwise, print “Invalid Input”
If the Batch number is not in the above-mentioned format, then display “Invalid Batch Code”
If the year is not between 2015 and 2020 (both inclusive), then display “Invalid Year”
If the month is not between 1 and 12 (both inclusive), then display “Invalid Month”
if the date is not between 1 and 31 (both inclusive), then display “Invalid Date”
Check the conditions in the above given order. If two or more invalid condition occurs in the same input, then display the first occurred invalid message.

Assumption: All characters in the input string will be in upper case.

Kindly do not add any additional methods in your code

Please do not use System.exit(0) to terminate the program.



Sample Input 1:

BD7A20171201

Sample Output 1:

Batch Number: BD7A

Expiry Date: 1/12/2017



Sample Input 2:

BD7A202

Sample Output 2:

Invalid Input



Sample Input 3:

B37A20181201

Sample Output 3:

Invalid Batch Code
 */