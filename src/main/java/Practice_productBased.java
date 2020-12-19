import java.util.*;

public class Practice_productBased
{
    public static int sumNrec(int n)
    {
        if(n<1)
            return 0;
        return n+sumNrec(n-1);
    }
    public static boolean isPalindrome(String str,int start, int end)
    {
        if(start<=end) return true;
        return (str.charAt(start)==str.charAt(end)) && isPalindrome(str,start+1,end-1);
    }
    public static int sumOfDigits(int n)
    {
        if(n<1) return 0;
        return n%10+ sumOfDigits(n/10);
    }
    public static int maxCuts(int n, int a,int b,int c)
    {
        if(n==0) return 0;
        if(n<0) return -1;
        int res=Math.max(maxCuts(n-a,a,b,c),Math.max(maxCuts(n-b,a,b,c),maxCuts(n-c,a,b,c)));
        if(res==-1) return -1;
        return res+1;
    }
    public static void subsetString(String str,String cur,int index)
    {
        if(index==str.length())
        {
            System.out.print(cur+" - ");
            return;
        }
        subsetString(str,cur,index+1);
        subsetString(str,cur.concat(String.valueOf(str.charAt(index))),index+1);

    }
    public static void TOH(int n,char source,char auxillery,char destination)
    {
        if(n==1)
        {
            System.out.println("Move disk: 1 from "+source+" to "+destination);
            return;
        }
        TOH(n-1,'A','C','B');
        System.out.println("Move disk: "+n+" from "+source+" to "+destination);
        TOH(n-1,'B','A','C');
    }
    public static int maxDiff(int arr[])
    {
        int res=arr[1]-arr[0];
        int min=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            res=Math.max(res,arr[i]-min);
            min=Math.min(min,arr[i]);
        }
        return res;
    }
    public static int maxCons1s(int []arr)
    {
        int max=0;
        int cur=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0) cur=0;
            else
            {
                cur++;
                max=Math.max(max,cur);
            }
        }
        return max;
    }
    public static int maxSumSubarray(int [] arr)
    {
        int maxSum=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            arr[i]=Math.max(arr[i-1]+arr[i],arr[i]);
            maxSum=Math.max(maxSum,arr[i]);
        }
        return maxSum;
    }
    public static int minSumSubarray(int [] arr)
    {
        int minSum=arr[0];
        for(int i=1;i<arr.length;i++)
        {
            arr[i]=Math.min(arr[i-1]+arr[i],arr[i]);
            minSum=Math.min(minSum,arr[i]);
        }
        return minSum;
    }
    public static int maxCircularSumSubarray(int [] arr)
    {
        int maxSum=0;
        int sum=0;
        for(int i:arr)
            sum+=i;
        maxSum=Math.max(sum-minSumSubarray(arr),maxSumSubarray(arr));
        return maxSum;
    }
    public static int maxEvenOdd(int []arr)
    {
        int cur=1;
        int max=1;
        for(int i=1;i<arr.length;i++)
        {
            if( (arr[i]%2!=0 && arr[i-1]%2==0) || (arr[i]%2==0 && arr[i-1]%2!=0) )
            {
                cur++;
                max=Math.max(max,cur);
            }
            else cur=1;
        }
        return max;
    }
    public static int majorityElement(int []arr)
    {
        int count=1;
        int res=0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]==arr[res])
                count++;
            else count--;
            if(count==0)
            {
                res=i;
                count=1;
            }
        }
        count=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==arr[res])
                count++;
        }
        if(count>arr.length/2)
            return res;
        return -1;
    }
    public static boolean isAnagram(String str1,String str2)
    {
        if(str1.length()!=str2.length()) return false;
        char [] c1=str1.toCharArray();
        char [] c2=str2.toCharArray();
        Arrays.sort(c2);
        Arrays.sort(c1);
        str1=new String(c1);
        str2=new String(c2);
        return str1.equals(str2);
    }
    public static boolean isAnagramOn(String str1,String str2)
    {
        if(str1.length()!=str2.length()) return false;
        int count[]=new int[256];
        for(int i=0;i<str1.length();i++)
        {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }
        for(int i:count)
        {
            if(i!=0)
                return false;
        }
        count['p']=1231;
        System.out.println("val: "+count['p']);
        return true;
    }
    static HashMap<Integer,Integer> ff=new HashMap<>();
    static int fibonacciMod(int a, int b, int n)
    {   int res;
        if(ff.containsKey(n))
        {
            return ff.get(n);
        }
        else
        {
            res=fibonacciMod(a,b,n-1)+fibonacciMod(a,b,n-2);
            ff.put(n,res);
        }
        return res;
    }
    static long arrayManipulation(int n, int[][] queries)
    {
        long[] computation = new long[n];

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0] - 1;
            int b = queries[i][1] - 1;
            int k = queries[i][2];

            computation[a] += k;
            if (b + 1 < n ) {
                computation[b + 1] -= k;
            }
        }
        long max = 0; long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += computation[i];
            max = Math.max(max, sum);
        }
        return max;

    }
    static String superReducedString(String s)
    {
        char[] ss=s.toCharArray();
        ArrayList<Character> str=new ArrayList<>();
        for(char i:ss)
            str.add(i);
        int c=1;
        int n=s.length();
        while(c<n)
        {
            if(str.get(c)==str.get(c-1))
            {
                str.remove(c-1);
                str.remove(c-1);
                n-=2;
            }
            else
                c++;
        }
        if(str.size()==0)
            return "Empty String";
        ss=new char[str.size()];
        for(int i=0;i<str.size();i++)
            ss[i]=str.get(i);
        return new String(ss);
    }
    static int hourglassSum(int[][] arr)
    {
        int n=arr.length;
        int sum=0;
        for(int i=1;i<n;i++)
        {
            for(int j=1;j<n-2;j++)
            {
                sum=Math.max(sum,arr[i][j]+arr[i-1][j-1]+arr[i-1][j+1]+arr[i+1][j-1]+arr[i+1][j+1]);
                System.out.println("a: "+arr[i-1][j-1]);
                System.out.println("b: "+arr[i-1][j+1]);
                System.out.println("c: "+arr[i][j]);
                System.out.println("d: "+arr[i+1][j-1]);
                System.out.println("e: "+arr[i+1][j+1]);
            }
        }
        return sum;
    }
    static int cookies(int k, int[] A)
    {
        int count=0;
        ArrayList<Integer> ar=new ArrayList<>();
        for(int i:A)
            ar.add(i);
        int c=0;
        int n=A.length;
        System.out.println(ar);
        while(c<n-1)
        {
            if(ar.get(c)<k)
            {
                ar.set(c+1,ar.get(c)+ar.get(c+1)*2);
                ar.remove(c);
                count++;
                Collections.sort(ar);
                System.out.println(ar);
                n--;
            }
            else
                c++;
        }
        if(ar.get(0)<k) return -1;
        return count;
    }
    public static int covidCheck(String str)
    {
        int count=0;
        char[] s=str.toCharArray();
        int [] arr=new int [5];
        for(int i=0;i<s.length;i++)
        {
            if(s[i]=='c') arr[0]++;
            else if (s[i]=='o') arr[1]++;
            else if (s[i]=='v') arr[2]++;
            else if (s[i]=='i') arr[3]++;
            else if (s[i]=='d') arr[4]++;
        }
        for(int i=0;i<s.length;i++)
        {
            if(arr[i]!=i) break;
            count++;
            if(i==4) i=0;
        }
        return count;
    }
    static String isBalanced(String s)
    {
        char[] arr=s.toCharArray();
        Stack<Integer> ss=new Stack();
        HashMap<Integer,Integer> ans=new HashMap<>();
        ans.put(41,40);
        ans.put(93,91);
        ans.put(125,123);
        System.out.println("empty: "+ss.empty());
        for(int i:arr)
        {
            if(i==40 || i==123 || i==91) {
                ss.push(i);
                //System.out.println("push: i:"+i);
            }
            else
            {
                if(ss.empty()) return "NO";
                int t=(int)ss.pop();
                //System.out.println("t: "+t+" i: "+i);
                if(i==41)
                    if(t!=ans.get(41))
                        return "NO";
                else if(i==93)
                    if(t!=ans.get(93))
                        return "NO";
                else if(i==125)
                    if(t!=ans.get(125))
                        return "NO";
            }
        }
        if(ss.empty())
            return "YES";
        return "NO";
    }
    static int twoStacks(int x, int[] a, int[] b)
    {
        int res=0;
        int count=1;
        int ahead=0;
        int bhead=1;

        while(res<=x)
        {
            if(a[ahead]>b[bhead])
                res+=a[ahead++];
            else
                res+=b[bhead++];
            count++;
        }
        return count;
    }
    static long largestRectangle(int[] h)
    {
        int n = h.length, i = 0, area = 0;
        Stack<Integer> s = new Stack<>();
        while (i < n) {
            while (!s.isEmpty() && h[i] < h[s.peek()])
            {
                area = Math.max(area, h[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));
            }
            s.push(i++);
        }
        while (!s.isEmpty())
        {
            area = Math.max(area, h[s.pop()] * (n - (s.isEmpty() ? 0 : s.peek() + 1)));
        }
        return area;
    }
    public static boolean isPrime(int n)
    {
        for(int i=2;i<Math.sqrt(n);i++)
        {
            if(n%i==0) return false;
        }
        return true;
    }
    public static int [] getPrimes(int n)
    {
        int [] primes=new int[n];

        int c=0;
        for(int i=2;c<n;i++)
        {
            if(isPrime(i)) primes[c++]=i;
        }
        System.out.println("Primes ok");
        return primes;
    }
    static int[] waiter(int[] number, int q)
    {
        int [] primes=getPrimes(q);
        int i=1;
        for(int j:primes) System.out.print(j+" ");
        ArrayList<Integer> biCont=new ArrayList<>();
        Stack<Integer> A=new Stack<>();
        Stack<Integer> B =new Stack<>();
        //  INITIALLY A0 VALUES IN STACK Ao
        for(int a:number)
            A.add(a);
        Stack<Integer> Abuffer=new Stack<>();
        boolean aFlag=true;
        while (i<=q)
        {
            System.out.println("run i: "+i);
            while (!A.isEmpty())
            {
                int element=A.pop();
                if(element%primes[i-1]==0) {
                    B.add(element);
                    System.out.println("Element insterted to B: "+element);
                }
                else {
                    Abuffer.add(element);
                    System.out.println("Element insterted to Abuffer: "+element);
                }
            }
            //Adding B stack to array list
            while (!B.isEmpty()) {
                biCont.add(B.pop());
            }
            if(Abuffer.isEmpty()) {  //setting a to false for empty
                aFlag = false;
            }
            else {
                A.clear();
                A= (Stack<Integer>) Abuffer.clone();
                Abuffer.clear();
            }
            i++;
        }
        System.out.println(biCont);
        int [] out=new int[biCont.size()+A.size()];
        Iterator<Integer> ite=biCont.iterator();
        i=0;
        for(int j=i;j<biCont.size();j++)
            out[j]=biCont.get(j);
        i=biCont.size();
        System.out.println("i: "+i+"  Both size: "+(biCont.size()+A.size()));
        System.out.println("Aflag: "+aFlag);
        while (!A.isEmpty() && i<=(biCont.size()+A.size()+1) && aFlag)  {
            out[i] = A.pop();
            System.out.println("Add out: "+out[i]+" i: "+i);
            i++;
        }
        return out;
    }
    public static void main(String ...a)
    {
//        System.out.println("Sum of natural numbers-10: "+sumNrec(10));
//        System.out.println("Palindrome check - BcaiacB: "+isPalindrome("BcaiacB",0,7));
//        System.out.println("Sum of digits: "+sumOfDigits(55553));
//        System.out.println("MaxCuts: "+maxCuts(5,1,5,3));
//        System.out.println("Substring: ");
//        subsetString("abc","",0);
//        System.out.println();
//        //TOH(4,'A','B','C');
//        System.out.println("Max Difference: j-i: "+maxDiff(new int[]{55,3,10,5,9,21}));
//        System.out.println("Max consecutive 1s: "+maxCons1s(new int[]{1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1}));
//        System.out.println("Max Sum Subarray: "+maxSumSubarray(new int[]{-5,1,-2,3,-1,2,-2}));
//        System.out.println("Min Sum Subarray: "+minSumSubarray(new int[]{8,-4,3,-5,4}));
//        System.out.println("Circular Max Sum Subarray: "+maxCircularSumSubarray(new int[]{5,-2,3,4}));
//        System.out.println("Max Odd Even Subarray: "+maxEvenOdd(new int[]{2,3,4,5,6,7,8,9,2,3,4,5,6,7,8,9}));
//        System.out.println("Majority element in a array i.e > n/2: "+majorityElement(new int[]{8,8,6,6,6,4,6}));
//        System.out.println("Anagram by sort: "+isAnagram("apple","leapp"));
//        System.out.println("Anagram by o(n): "+isAnagramOn("apple","leapp"));
//        ff.put(0,920881302);
//        ff.put(1,970435252);
//        //System.out.println("Fib mod: "+fibonacciMod(920881302, 970435252, 891913774));
//        System.out.println("Super reduced string: "+superReducedString("aaabccddd"));
//        System.out.println("don: "+cookies(10,new int[]{1 ,1,1}));
//        System.out.println("Balanced bracket: "+isBalanced("{{[[(())]]}}"));
//        System.out.println(Math.max(-88,-26));
        System.out.println("lARGEST RECTANGLE: "+largestRectangle(new int[]{1,4,4,3,5,7}));
//        System.out.println("divisible: "+(2%3==0));
//        System.out.println("Waiter: ");
//        for(int i:waiter(new int[]{3,3,4,4,9},2))
//            System.out.print(i+"- ");
    }
}
/*
public static ArrayList<Integer> findNPrimes(int n) {
    ArrayList<Integer> primes = new ArrayList<Integer>();
    int i = 2;
    while (primes.size() < n) {
      BigInteger tmp = new BigInteger("" + i);
      if (tmp.isProbablePrime(1000)) {
        primes.add(i);
      }
      i++;
    }

    return primes;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int q = in.nextInt();
    Stack<Integer> A = new Stack<Integer>();
    ArrayList<Integer> primes = findNPrimes(q);
    for (int i = 0; i < n; i++) {
      A.push(in.nextInt());
    }

    ArrayList<Stack<Integer>> B = new ArrayList<Stack<Integer>>();
    for (int i = 0; i < q; i++) {
      Stack<Integer> tmpA = new Stack<Integer>();
      Stack<Integer> BI = new Stack<Integer>();
      while (!A.empty()) {
        if (A.peek() % primes.get(i) == 0) {
          BI.push(A.pop());
        } else {
          tmpA.push(A.pop());
        }
      }

      B.add(BI);
      A = tmpA;
    }

    for (int i = 0; i < q; i++) {
      while (!B.get(i).empty()) {
        System.out.println(B.get(i).pop());
      }
    }

    while (!A.empty()) {
      System.out.println(A.pop());
    }
  }
 */