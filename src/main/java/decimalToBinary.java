import java.util.Scanner;

public class decimalToBinary
{
    public static void conventionalConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Conventional conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = n % 2;
            b = b + d * (int) Math.pow(10, c++);
            n /= 2;
        } //converting decimal to binary
        System.out.println("\tBinary number: " + b);
    }

    public static void bitwiseConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Bitwise conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = (n & 1);
            b += d * (int) Math.pow(10, c++);
            n >>= 1;
        }
        System.out.println("\tBinary number: " + b);
        input.close();
    }

    public static void main(String ...dd)
    {
        conventionalConversion();
        //bitwiseConversion();
        System.out.println(12/5);
        System.out.println(1%5);
        System.out.println(Math.pow(2,3));
    }
}
