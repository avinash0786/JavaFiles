package GEEKS_FOR_GEEKS;

public class recursion
{
    public static boolean prime(int n)
    {
        System.out.println("n: "+n);
        if(n==2 || n==3)
            return true;
        System.out.println("sqrt/:  "+ (int)Math.sqrt(n));
        for(int i=2; i<(n/2);++i)
        {
            if(n%i==0)
            {
                System.out.println("returning false  i:"+i);
                return false;
            }
        }
        System.out.println("returning true");
        return true;
    }

    public static int exactly3Divisors(int n)
    {
        int count=1;
        for(double i=n;i>1;i--)
        {   System.out.println("i sending : "+i);
            if(prime((int) Math.sqrt(i)))
            {
                count++;
                System.out.println("i: "+i+" count: "+count);
            }

        }
        return count;


        /*boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int p=2; p*p<=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if (prime[p])
            {
                // Update all multiples of p
                for (int i=p*2; i<=n; i += p)
                    prime[i] = false;
            }
        }
        int count=0;
        // print squares of primes upto n
        for (int i=0;  i*i <= n ; i++)
            if (prime[i])
                count++;
        return count;*/
    }
    public static double termOfGP(int A,int B,int N)
    {
        //Your code here
        double gp;
        if(N==1)
            return A;
        double ratio=(double)B/(double)A;
        System.out.println("ra: "+ratio);
        gp=A*Math.pow(ratio,N-1);
        return gp;
    }

    public static void printNos(int N)
    {
        if(N > 0)
        {
            printNos(N-1);
            System.out.println(N);
        }
        return;
    }
    public static int sumOfDigits(int n)
    {
        //  if n is less than 10 return n
        if(n<10) return n;
        else
            return n%10+sumOfDigits(n/10);   // rec sol ok --

    }
    public static int countDigits(int n)
    {
        // add your code here
        int c=0;
        while (n!=0)
        {
            n/=10;
            c++;
        }
        return c;
    }

    public static int digitalRoot(int n)
    {
        int s=sumOfDigits(n);
        //System.out.println("fs: "+s);
        while (s>=10)
        {
            s=sumOfDigits(s);
            //System.out.println("s: "+s);
            //System.out.println("s: "+s);
        }
        return s;
    }

    static long fibonacci(int n)
    {
        long fib;
        if(n==1 || n==2)
            return 1;
        else return fibonacci(n-1)+fibonacci(n-2);
    }

    public static long reverse(long n)
    {
        long revnum=0;
        while(n>0)
        {
            revnum=revnum*10+n%10;
            n/=10;
        }
        return revnum;
    }

    long power(int N,int R)
    {
        long pow=N;
        for(int i=0;i<R;i++)
        {
            pow*=N;
        }
        return  (long) pow%1000000007;

    }
    public static void toh(int n, char src,char aux, char dest)
    {
        if(n==1)
        {
            System.out.println("Move disk 1 form "+src+" to "+dest);
            return;
        }
        toh(n-1,src,dest,aux);
        System.out.println("Move disk "+n+" from "+src+" to "+dest);
        toh(n-1,aux,src,dest);
    }
    public static int joshepus(int n,int k)
    {
        if(n==1)
            return 0;
        else return (joshepus(n-1,k)+k)%n;
    }
    public static int countSubsetSum(int []arr,int n,int sum)
    {
        if(n==0) return (sum==0)?1:0;
        return countSubsetSum(arr,n-1,sum)+countSubsetSum(arr,n-1,sum-arr[n-1]);
    }
    public static void main(String ...ss)
    {   /*System.out.println(9-95);
        //printNos(5);
        System.out.println(digitalRoot(27640));
        System.out.println(termOfGP(84,87,3));  */
        //System.out.println(exactly3Divisors(49));
        toh(4,'A','B','C');
        System.out.println(joshepus(5,3));
        System.out.println(countSubsetSum(new int[]{10,15,20},3,25));
    }
}
    /*
    * Try using modulus property with fast exponentian:

Property 1:
(m * n) % p has a very interesting property:
(m * n) % p =((m % p) * (n % p)) % p

Property 2:
if b is even:
(a ^ b) % c = ((a ^ b/2) * (a ^ b/2))%c ? this suggests divide and conquer
if b is odd:
(a ^ b) % c = (a * (a ^( b-1))%c

Property 3:
If we have to return the mod of a negative number x whose absolute value is less than y:
then (x + y) % y will do the trick

Note:
Also as the product of (a ^ b/2) * (a ^ b/2) and a * (a ^( b-1) may cause overflow, hence we must be careful about those scenarios*/