import java.util.Scanner;

public class binaryToDecimal
{
    public static void main(String ...ss)
    {
        Scanner sc = new Scanner(System.in);
        int binNum, binCopy, d, s = 0, power = 0;
        System.out.print("Binary number: ");
        binNum = sc.nextInt();
        binCopy = binNum;
        while (binCopy != 0) {
            System.out.println("N: "+binCopy);
            d = binCopy % 10;
            System.out.println("d: "+d);
            s += d * (int) Math.pow(2, power++);
            System.out.println("s: "+s);
            binCopy /= 10;
            System.out.println("n: "+binCopy);
            System.out.println("-----------------------------");
        }
        System.out.println("Decimal equivalent:" + s);
        sc.close();
    }
}
