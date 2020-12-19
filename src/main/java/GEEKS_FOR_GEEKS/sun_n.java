package GEEKS_FOR_GEEKS;

public class sun_n
{

    public static int modInverse(int a, int b)
    {
        long M = 1000000007;

        return (int)((a%M)+(b%M)%M);
        //Your code here
    }

    public static int exactly3Divisors(int N)
    {
        int count=0;
        for(int i=N;i>0;i--)
        {
            int div=0;
            if(isPrime((int) Math.sqrt(N)))
            {
                count++;
                System.out.println("i: "+i);
            }
        }
        return count;
    }

    public static boolean isPrime(int N)
    {
        if(N==1 || N==0)
            return false;
        for(int i=2;i<=Math.sqrt(N);i++)
        {
            if(N%i==0)
                return false;
        }
        return true;
    }


    public static int digitsInFactorial(int N)
    {
        double count=0;
        for(double i=N; i>0;i--)
            {
                count+=Math.log10(i);
            }
        return (int)(count+1);
        /*long fact=factorial (N);
        System.out.println(fact);
        int count=0;
        while(fact!=0)
        {
            fact=fact/10;
            ++count;
        }
        return count;*/
    }
    public static long factorial (int N)
    {
        if(N==0 || N==1)
            return 1;
        return N*factorial (N-1);
    }
    public static void quadraticRoots(int a,int b,int c)
    {
        if(b*b-4*a*c<0)
            System.out.println("Imaginary");
        //System.out.println("d: "+d);
        else {
            double d=Math.sqrt(b*b-4*a*c);
            System.out.println("d: "+d);
            double rta =(-b+d)/(2*a);
            double rtb=(-b-d)/(2*a);
            System.out.println("a: "+rta+" b: "+rtb);
            System.out.println( (int)Math.floor(rta)+" "+(int)Math.floor(rtb));
        }
    }

    public static int sumNnum(int n)
    {
        return n*(n+1)/2;
    }

    public static int countDigit(int n)
    {
        int count=0;
        while(n!=0)
        {
            n=n/10;
            ++count;
        }
        return count;
    }
    public static int recCountDigit(int n)
    {
        if(n==0)
            return 0;
        else
            return 1+recCountDigit(n/10);

    }
    public static int logCountDigit(int n)
    {
            return (int) Math.floor(Math.log10(n) +1.0);
    }

    public static void main(String ...ff)
    {
        System.out.println(sumNnum(3));
        System.out.println(countDigit(46563));
        System.out.println(recCountDigit(46563));
        System.out.println(logCountDigit(1232));
        quadraticRoots(543, 199, 843);
        System.out.println(digitsInFactorial(42));
        System.out.println("ans /"+exactly3Divisors(10));
    }
}


///reverse number
/*
*           int n, rev;
            rev = 0;
            while (n > 0)
            {
             rev = rev*10 + n%10;
             n = n/10;
            }
            *
 //EVERY PRIME NUMBER CAN BE REPRESENTED BY 6N+1 OR 6N-1 EXCEPT 2 &3
* */

