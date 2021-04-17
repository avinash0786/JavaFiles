import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    public static void genPrimes(int n, ArrayList<Integer> primes){
        boolean[] arr=new boolean[n+1]; //  setting all nos prime first
        arr[0]=true;
        arr[1]=true;    //  setting 0 & 1 as non-prime/ composite
        /*
        if 2 is a prime no then 4,6,8,10  will no be prime as they are multiple of 2
        lly, 3 is a prime then 9,12,15,18 will not be prime
         */
        for (int i = 2; i*i<=n ; i++) {
            if (!arr[i]){
//                System.out.println("i: "+i+"*****");
                for (int j = i*i; j <=n ; j+=i) {
//                    System.out.println("j: "+j);
                    arr[j]=true;
                }
            }
        }
        for (int i = 2; i <=n ; i++) {
            if (!arr[i])
                primes.add(i);
        }
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
            if (n%i==0){
                while (n%i==0)
                    n/=i;
                result -=result/i;
            }
        }
        if (n>1)
            result -=result/n;
        return result;
    }
    //        Arrays.setAll(arr, i -> i+2); // The array becomes {2, 3, 4 }

    static int[]phiArray=new int[100];
    public static void phiN(){
//        int[] phi=new int[n+1];
        Arrays.setAll(phiArray,i->i);
        for (int i =2; i <=phiArray.length-5; i++) {
            if (phiArray[i]==i){
                for (int j = i; j <=phiArray.length-5 ; j+=i) {
                    phiArray[j]=phiArray[j]/i;
                    phiArray[j]=phiArray[j]*(i-1);
                    //phi[j]-=phi[j]/i;   ONE LINE
                }
            }
        }
//        System.out.println(Arrays.toString(phiArray));
    }
    public static void phiOnetoN(int n){    //in O(nlogn) using Divisior sum property
        int []phi=new int[n+1];
        phi[0]=0;
        phi[1]=1;
//        for (int i = 2; i <=n; i++)
//            phi[i]=i-1;
        Arrays.setAll(phi,i->i);
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
        phiN();
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
        return phiArray[n/d];
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
    public static void divisors(int n){
        ArrayList<Integer> op=new ArrayList<>();
        for (int i = 1; i*i <=n; i++) {
            if (n%i==0){
                op.add(i);
                if (i!=n/i)
                    op.add(n/i);
            }
        }
        System.out.println(op);
    }
    public static void divisorsEffic(int n){
        ArrayList<Integer> op=new ArrayList<>();
        int i;
        for (i = 1; i*i<n; i++) {
            if (n%i==0)
                op.add(i);
        }
        i--;
        for (   ; i>=1; i--) {
            if (n%i==0) {
                op.add(n / i);
            }        }
        System.out.println(op);
    }

    public static int trailingZero(int n){  //trailing zeros in factorial
        int res=0;
        for (int i = 5; i <=n; i*=5) {
            res+=n/i;
        }
        return res;
    }
    //  0   1   2   3
    //  x   y   x1  y1
    public static int extendedEuclid(int a, int b){
        return 0;
    }

    public static int catalan(int n){
        int[] Cat=new int[n+1];
        Cat[0]=Cat[1]=1;
        for (int i =2; i <=n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(Arrays.toString(Cat));
                Cat[i]+=Cat[j]*Cat[i-j-1];
            }
        }
        System.out.println(Arrays.toString(Cat));
        return Cat[n];
    }
    public static void genPermutation(String digits,int i,int index,int cur,ArrayList<Integer> op,int lev,ArrayList<Integer> fin, int k){
        if (index>lev)
            return;
        cur=cur*10+Integer.parseInt(String.valueOf(digits.charAt(i)));
        op.add(cur);
        if (cur%k==0)
            fin.add(cur);
//        System.out.println("Cur: "+cur);
        genPermutation(digits,0,index+1,cur,op,lev,fin,k);
        genPermutation(digits,1,index+1,cur,op,lev,fin,k);
        genPermutation(digits,2,index+1,cur,op,lev,fin,k);
    }
    public static void main(String[] args) {
//        ArrayList<Integer> op=new ArrayList<>();
//        ArrayList<Integer> fin=new ArrayList<>();
//        ArrayList<Integer> primes=new ArrayList<>();
//        genPrimes(100,primes);
//        System.out.println(primes);
//        BigDecimal t= BigDecimal.valueOf(System.currentTimeMillis());
//        genPermutation("456",0,0,0,op,1,fin,2);
//        genPermutation("456",1,0,0,op,1,fin,2);
//        genPermutation("456",2,0,0,op,1,fin,2);
//        Collections.sort(op);
//        Collections.sort(fin);
//        System.out.println(op);
//        System.out.println(fin);
//        System.out.println("Exec time: "+(BigDecimal.valueOf(System.currentTimeMillis()).subtract(t)));
//        System.out.println("GCD sum: "+gcdSum(12));
//        System.out.println("Catalan 4: "+catalan(4));
//        phiN(10);
//        int[]tem=new int[4];
//        int d=extendedEuclid(81,57,tem);
//        System.out.printf("Solution of %dx + %dy = %d is x= %d , y= %d ",81,57,d,tem[2],tem[3]);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println();
//        divisorsEffic(90);
//        System.out.println(trailingZero(251));
//        Scanner inp=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        System.out.println(modGcd(10,1,1));
//        System.out.println(power(2,5));
//        System.out.println(gcd(10,10));
//        System.out.println(lcm(12,24));
//        seivePrime(300);
        primeFactor(36);
//        wheelFactorisation(56);
//        System.out.println(eulerTotient(10));
//        phiOnetoN(7);
//        System.out.println(moduloMulInv(2,5));
//        calaulateFact();
//        System.out.println("Prime: "+isPrime(17));
//        System.out.println("fermat prime: "+fermatPrimality(22));
    }
}
