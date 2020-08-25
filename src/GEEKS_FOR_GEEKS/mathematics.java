package GEEKS_FOR_GEEKS;

import java.util.ArrayList;
import java.util.HashMap;

public class mathematics
{
    public static int nTernAP(int ap[],int n)
    {
        int a=ap[0];
        int val;
        int d=ap[1]-a;
        val=a+(n-1)*d;
        return val;
    }
    public static double sumAP(int ap[],double n)
    {
        double s=0;
        s=n/2*(ap[0]+nTernAP(ap, (int) n));
        return s;
    }
    public static double nTermGP(double []gp,double n)
    {
        double r=gp[1]/gp[0];
        double term;
        term=gp[0]*Math.pow(r,n-1);
        return term;
    }
    public static double sumGP(double []gp,double n)
    {
        double r=gp[1]/gp[0];
        double sum=0;
        if(r<0)
        {
            sum=gp[0]*(Math.pow(r,n)-1)/(r-1);
            return sum;
        }
        sum=gp[0]*(1-Math.pow(r,n))/(1-r);
        return sum;
    }
    public static int GCDsub(int a,int b)
    {
        a=(a>0)?a:-1;
        b=(b>0)?b:-b;
        while(a!=b)
        {
            if(a>b)
                a-=b;
            else
                b-=a;
        }
        return a;
    }
    public static int GCDdiv(int a,int b)
    {   System.out.println("a: "+a+" b:"+b);
        if(a==0)
            return b;
        return GCDdiv(b%a,a);
    }
    public static int fact(int n)
    {
        System.out.println("n: "+n+" n-1: "+(n-1));
        if(n<2)
            return 1;
        return n*fact(n-1);
    }
    public static ArrayList<Integer> primeFactors(int n)
    {
        // NO of 2 that divide n
        ArrayList<Integer> fac=new ArrayList<>();
        while(n%2==0)
        {
            fac.add(2);
            //System.out.println(n);
            n/=2;
        }
        // Other prime factors
        for(int i=3;i<=Math.sqrt(n);i+=2)
        {
            while(n%i==0)
            {
                fac.add(i);
                //System.out.println(n);
                n/=i;                   //repeated till n becomes either 1 or a prime number.
            }
        }
        // This condition is to handle the case when    **for prime number because is is not divisible by any
        // n is a prime number greater than 2
        if (n > 2)
            fac.add(n);
        //System.out.println("PrimeFactors: "+fac);
       // occurence(fac);
        return fac;
    }
    public static HashMap<Integer,Integer> occurence(ArrayList<Integer> arr)
    {
        HashMap<Integer,Integer> oc=new HashMap<>();
        for(int e:arr)
        {
            if(oc.containsKey(e))
            {
                oc.replace(e,oc.get(e)+1);
            }
            else {
                oc.put(e,1);
            }
        }
        //System.out.println("Occurances: ");
        /*for(int i:oc.keySet())
            System.out.print(i+" : "+oc.get(i)+" , ");*/
        return oc;
    }
    public static int exactly3Divisor(int n) // How many numbers in between 1-n having exactly 3 divisor
    {
        int fCount=0;       //a number is having 3 divisor if the number is square of a prime number
        for(int i=2;i<=n;i++)
        {
            if(isprime( Math.sqrt(i)))
                fCount++;
        }
        return fCount;
    }
    public static int NEWexactly3Divisor(int n) // How many numbers in between 1-n having exactly 3 divisor
    {
        int fCount=0;
        for(int i=2;i<=n;i++)
        {
            if(noOfDivisor(i)==3) {
                fCount++;
                System.out.println("Num: "+i);
            }
        }
        return fCount;
    }

    public static boolean checkInt(double n)
    {
        int t=(int)n;
        return n == t;
    }
    public static boolean isprime(double n)
    {
        if(!checkInt(n))
            return false;
        if(n<2)
            return false;
        for(int i=2;i<=Math.sqrt(n);i++)
        {
            if(n%i==0)
                return false;
        }
        return true;
    }
    public static int noOfDivisor(int n)
    {
        ArrayList<Integer> ans=primeFactors(n);
        HashMap<Integer,Integer> op=occurence(ans);
        //System.out.println("OCCURANCEs:  "+op);
        int res=1;
        for(int p:op.values())
            res*=p+1;
        return res;
    }
    public static void main(String ...aa)
    {
        /*System.out.println(nTernAP(new int[]{1,4},4));
        System.out.println(sumAP(new int[]{1,2,3},10));
        System.out.println(nTermGP(new double[]{110,220},5));
        System.out.println(sumGP(new double[]{110,220},2));
        System.out.println("GCD 81, -153: "+GCDsub(81,-153));
        System.out.println("GCD div: "+GCDdiv(81,153));
        System.out.println("Factorial: "+fact(5));
        primeFactors(60);*/
        System.out.println();
        System.out.println(exactly3Divisor(25));
        System.out.println(noOfDivisor(25));
        System.out.println("Answer: "+NEWexactly3Divisor(300));
    }
}
/*
--Every composite number has at least one prime factor less than or equal to square root of itself.
 */