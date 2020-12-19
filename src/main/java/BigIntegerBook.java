import java.math.BigInteger;

public class BigIntegerBook

{
    public static void main(String[] args) {
        long longVal=Long.MAX_VALUE;
        BigInteger valFormLong= BigInteger.valueOf(longVal);
        System.out.println(valFormLong);    //9223372036854775807

        int intValue=Integer.MAX_VALUE;
        BigInteger valformInt= BigInteger.valueOf(intValue);
        System.out.println(valformInt);     //2147483647

//        String strVal="-9999999999999999999999999999999991222222222222222222222666666666666666666666666666666666666666666666666666666666666666662222231";
//        BigInteger strVa=new BigInteger(strVal);
//        System.out.println(strVa);
//        System.out.println(strVa.subtract(BigInteger.valueOf(10)));

        BigInteger value1 = new BigInteger("17");
        BigInteger value2 = new BigInteger("4");
        BigInteger div = value1.divide(value2);     //Division: 17/4 = 4
        System.out.println(div);
    }
}
