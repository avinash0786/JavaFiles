import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NumberTheoryCP {
    //Binary Exponentiation:- to calculate a^n in O(log n)
    public static int power(int a,int n){
        int result=1;
        while (n>0){
            if (n%2==1)
                result*=a;
            a*=a;
            n/=2;
        }
        return result;
    }
    public static int recPower(int a, int b){
        if (b==0)
            return 1;
        int tmp=recPower(a,b/2);
        int result=tmp*tmp;
        if (b%2==1)
            result*=a;
        return result ;
    }
    static int countSetBits(int n)
    {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }
    public static int gcd(int a,int b){
//        System.out.println("a: "+a+" b: "+b);
        return (b!=0)?gcd(b,a%b):a;
    }
    public static int lcm(int a,int b){
        return (a*b)/gcd(a,b);
    }

    //Seive of Eratosthenes
    public static void seivePrime(int n){
        ArrayList<Integer> primes=new ArrayList<>();    //Initially all prime
        boolean []arr= new boolean[n+1];  // true==composite, false=prime no.
        arr[0]=true;
        arr[1]=true;
        for (int i =2; i*i <=n; i++) {
            if (!arr[i]){   //if arr[i] is prime
                for (int j = i*i; j <=n; j+=i) {
                    arr[j]=true;
                }
            }
        }
        for (int i = 2; i <=n ; i++) {
            if (!arr[i])
                primes.add(i);
        }
        System.out.println(primes);
    }
    public static void primeFactor(int n){
        ArrayList<Integer> op=new ArrayList<>();
        for (int i = 2; i*i <= n; i++) {
            while (n%i==0){
                op.add(i);
                n=n/i;
            }
        }
        if (n>1)
            op.add(n);
        System.out.println(op);
    }
    public static void wheelFactorisation(int n){
        ArrayList<Integer> op=new ArrayList<>();
        while (n%2==0){ //2 is the only even no so first check only 2 and skip other 2 multiple as other woulb be odd
            op.add(2);
            n/=2;
        }
        for (int i = 3; i*i <= n; i+=2) {
            while (n%i==0){
                op.add(i);
                n/=i;
            }
        }
        if (n>1)
            op.add(n);
        System.out.println(op);
    }
    // This method can be extended . if the number is not divisible by 3,
    // we can also ignore other multiple of 3
    /*
    We can also improve efficeiency by precomputing the primes and only checking multiple for that
     */

    //Euler totient function
    public static int eulerTotient(int n){  //logic: phi(N)= N - number of integer not coprime with N
        int result=n;
        for (int i = 2; i*i <= n; i++) {
            System.out.println("Res: "+result+" i: "+i+" n: "+n);
            if (n%i==0){
                System.out.println("N: "+n);
                while (n%i==0)
                    n/=i;
                System.out.println("N is: "+n+" Dec result by: "+result/n);
                result -=result/i;
            }
        }
        if (n>1)
            result -=result/n;
        return result;
    }
    public static void phiOnetoN(int n){    //in O(nlogn) using Divisior sum property
        int []phi=new int[n+1];
        phi[0]=0;
        phi[1]=1;
        for (int i = 2; i <=n; i++)
            phi[i]=i-1;
        for (int i = 2; i <=n; i++) {
            for (int j = i*2; j <=n; j+=i) {
                phi[j]=phi[j]-phi[i];
            }
        }
        for (int i : phi) {
            System.out.print(i+" ");
        }
    }
    static final int mod=1000000007;
    public static int modPow(int a,int n,int m){
        int res=1;
        while (n>0){
            if (n % 2 == 1) {
                res=((res%m)*(a%m))%m;
                n--;
            }
            a=((a%m)*(a%m))%m;
            n/=2;
        }
        return res;
    }
    public static int modGcd(int a,int b, int n){
        if (a==b){
            return (modPow(a,n,mod)+modPow(b,n,mod))%mod;
        }
        int candidate=1;
        int num=a-b;
        for (int i = 1; i*i <=num; i++) {
            if (num%i==0){
                int temp=(modPow(a,n,i)+modPow(b,n,i))%i;
                if (temp==0){
                    candidate=Math.max(candidate,i);
                }
                temp=(modPow(a,n,num/i)+modPow(b,n,num/i))%(num/i);
                if (temp==0){
                    candidate=Math.max(candidate,num/i);
                }
            }
        }
        return candidate%mod;
    }

    public static int moduloMulInv(int a,int m){
        return modPow(a,m-2,m);
    }
    public static long[]factortials=new long[1000001];
    public static void calaulateFact(){
        factortials[0]=factortials[1]=1;
        for (int i = 2; i <1000001; i++) {
            factortials[i]=(i*factortials[i-1]);
        }
        for (int i = 0; i < 300; i++) {
            System.out.println(factortials[i]+" ");
        }
    }
    public static int gcdSum(int N){
        int res=0;
        for (int i = 1; i*i <=N; i++) {
            if (N%i==0){
                int d1=i;   //we need to make sure that if d2 and d1 are same we evaluate only  once
                int d2=N/i;
                res+=d1*getCount(d1,N);
                // How many number from 1-12 such that the gcd with n==d1
                if (d1!=d2){    //in case of perfect square
                    res+=d2*getCount(d2,N);
                }
            }
        }
        return res;
    }

    private static int getCount(int d, int n) {
        //return phi[n/d]  calculete phi array to access the value
        return 0;
    }

    public static boolean isPrime(int n){   //trial divison
        for (int i = 2; i*i <=n; i++) {
            if (n%i==0)
                return false;
        }
        return true;
    }

    public static boolean fermatPrimality(int n){
        int iter=5;
        if (n<4)
            return n==2 || n==3;
        Random random=new Random();
        for (int i = 1; i <=iter; i++) {
            int a=2+ random.nextInt()%(n-3);
            int res=modPow(a,n-1,n);
            if (res!=1)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
//        System.out.println(Long.MAX_VALUE);
        Scanner inp=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        System.out.println(modGcd(10,1,1));
//        System.out.println(power(2,5));
//        System.out.println(gcd(9,10));
//        System.out.println(lcm(12,24));
//        seivePrime(300);
//        primeFactor(36);
//        wheelFactorisation(56);
        System.out.println(eulerTotient(10));
//        phiOnetoN(7);
//        System.out.println(moduloMulInv(2,5));
//        calaulateFact();
//        System.out.println("Prime: "+isPrime(17));
//        System.out.println("fermat prime: "+fermatPrimality(22));
    }
}
