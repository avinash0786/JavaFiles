import java.math.BigDecimal;
import java.math.RoundingMode;

public class bigDecimalBook {
    public static void main(String[] args) {
        BigDecimal a=new BigDecimal(12);
        System.out.println(a);

        System.out.println(a.compareTo(new BigDecimal(13)));
        System.out.println(a.compareTo(new BigDecimal(11)));
        System.out.println(a.compareTo(new BigDecimal(12.00)));
        System.out.println(BigDecimal.valueOf(17897L));

        BigDecimal aa=new BigDecimal(32);
        BigDecimal bb=new BigDecimal(89);
        System.out.println("add: "+a.add(bb));
        System.out.println("sub: "+aa.subtract(bb));
        System.out.println("mul: "+aa.multiply(bb));
        ///Equivalent to result = a / b (Upto 10 Decimal places and Round HALF_UP)
        System.out.println("div: "+aa.divide(bb,10, RoundingMode.HALF_UP));
        /*
        Division is a bit more complicated than the other arithmetic operations,
        Non-terminating decimal expansion; no exact representable
        decimal result.
        This would work perfectly well when the result would be a terminating decimal say if I wanted to divide 5 by 2, but
        for those numbers which upon dividing would give a non terminating result we would get an ArithmeticException.
        In the real world scenario, one cannot predict the values that would be encountered during the division, so we need
        to specify the Scale and the Rounding Mode for BigDecimal division.

         */

        System.out.println("Remainder: "+a.remainder(aa));
        System.out.println("Power: "+a.pow(12));

    }
}
/*
The BigDecimal class provides operations for arithmetic (add, subtract, multiply, divide), scale manipulation,
rounding, comparison, hashing, and format conversion. The BigDecimal represents immutable, arbitrary-precision
signed decimal numbers. This class shall be used in a necessity of high-precision calculation.
 */